package com.music.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.music.dao.AlbumDao;
import com.music.dao.SongDao;
import com.music.entity.*;
import org.apache.tomcat.util.codec.binary.Base64;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.datatype.Artwork;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.*;

@Service
public class SongService {
    @Resource
    private SongDao songDao;
    @Resource
    private AlbumDao albumDao;

    /**
     * 根据歌曲名获取
     *
     * @param name
     * @return
     * @throws Exception
     */
    public Map<String, Object> getPic(String name) throws Exception {
        File audioFile;
        if (new File("D:/Music/" + name + ".flac").exists()) {
            audioFile = new File("D:/Music/" + name + ".flac");
        } else if (new File("D:/Music/" + name + ".mp3").exists()) {
            audioFile = new File("D:/Music/" + name + ".mp3");
        } else {
            return null;
        }
        // 要读取元数据的音频文件路径
//        File audioFile = new File("D:/Music/"+name+".mp3");
        // 读取音频文件元数据
        AudioFile audio = AudioFileIO.read(audioFile);
        Tag tag = audio.getTag();

        String Title = tag.getFirst(FieldKey.TITLE);//歌名
        String Artist = tag.getFirst(FieldKey.ARTIST);//歌手
        String Album = tag.getFirst(FieldKey.ALBUM);//专辑
        // 获取专辑封面图片
        Artwork artwork = tag.getFirstArtwork();


        // 将专辑封面图片保存到文件
//            ImageIO.write(artwork.getImage(), "jpg", new File("D:/Music/Letting Go - 蔡健雅.jpg"));

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            // 设置图片的格式
            ImageIO.write(artwork.getImage(), "jpg", stream);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        byte[] bytes = Base64.encodeBase64(stream.toByteArray());
        String base64 = new String(bytes);
        String img = "data:image/jpeg;base64," + base64;
        Map<String, Object> map = new HashMap<>();
        map.put("Title", Title);
        map.put("Artist", Artist);
        map.put("Album", Album);
        map.put("img", img);
        return map;


    }

//    /**
//     * 根据歌手分页查询
//     * @param myPage 传入第几页、每页大小
//     * @return 一页数据
//     */

    /**
     * 根据歌手分页查询歌曲
     *
     * @param myPage 传入第几页、每页大小
     * @param singer 歌手名
     * @return 一页数据
     */
    public Map<String, Object> selectSongsMap(MyPage myPage, String singer) {
        Page<Song> page = new Page<>(myPage.getPage(), myPage.getSize());
        Song song = new Song();
        song.setSinger(singer);
        QueryWrapper<Song> queryWrapper = new QueryWrapper<>(song);
        Page<Song> page1 = songDao.selectPage(page, queryWrapper);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", page1.getPages());
        map.put("row", page1.getRecords());
        return map;

    }


    /**
     * 查询所有专辑-分页
     *
     * @param myPage 传入第几页、每页大小
     * @return 一页数据
     */
    public Map<String, Object> selectAllAlbum(MyPage myPage) {
        Page<Album> page = new Page<>(myPage.getPage(), myPage.getSize());
        QueryWrapper<Album> queryWrapper = new QueryWrapper<>();
        Page<Album> page1 = albumDao.selectPage(page, queryWrapper);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", page1.getPages());
        map.put("row", page1.getRecords());
        return map;
    }

    /**
     * 根据专辑分页查询歌曲
     *
     * @param myPage 传入第几页、每页大小
     * @param album  专辑
     * @return 一页数据
     */
    public Map<String, Object> selectByAlbum(MyPage myPage, String album) {
        if (album != null) {
            Page<Song> page = new Page<>(myPage.getPage(), myPage.getSize());
            Song song = new Song();
            song.setAlbum(album);
            QueryWrapper<Song> queryWrapper = new QueryWrapper<>(song);
            Page<Song> page1 = songDao.selectPage(page, queryWrapper);
            HashMap<String, Object> map = new HashMap<>();
            map.put("total", page1.getPages());
            map.put("row", page1.getRecords());
            return map;
        } else {
            return null;
        }

    }

    /**
     * 根据音乐获取歌词
     * @param name 音乐
     * @return 歌词链表
     * @throws FileNotFoundException e
     */
    public List<MyLyric> getLrc(String name) throws FileNotFoundException {
        String path = "D:\\Music\\" + name + ".lrc";
        FileReader file = new FileReader(path);
        Scanner sc = new Scanner(file);
        List<String> list = new ArrayList<>();
        while (sc.hasNext()) {
            String line = sc.nextLine();
            int len = line.split("]").length;
            if (len > 1) {
                list.add(line);
            }
        }
        List<MyLyric> lyrics = new ArrayList<>();
        for (String li : list) {
            String time = li.split("]")[0].split("\\[")[1];
            int time1 = Integer.parseInt(time.split(":")[0]);
            int time2 = Integer.parseInt(time.split(":")[1].split("\\.")[0]);
            int time3 = Integer.parseInt(time.split(":")[1].split("\\.")[1]);
            int Time = time1 * 60 * 1000 + time2 * 1000 + time3;
            String lyric = li.split("]")[1];
//            System.out.println(time+"-----"+Time + "---" + lyric);
            lyrics.add(new MyLyric(Time, lyric));
        }

        return lyrics;
    }

    /**
     * 搜索功能
     * @param data 数据
     * @return list
     */
    public Set<Song> SelectByUser(String data){
        data = "%"+data+"%";
        List<Song> list1 =  songDao.SelectBySong(data);
        List<Song> list2 = songDao.SelectBySinger(data);
        Set<Song> set =new HashSet<>();
        set.addAll(list1);
        set.addAll(list2);
        return set;
    }

    /**
     * 查询歌曲数量
     * @return int
     */
    public int selectSongNum(){
        return songDao.selectList(null).size();
    }

    /**
     * 查询歌单数量
     * @return int
     */
    public int selectAlbumNum(){
        return albumDao.selectList(null).size();
    }


    /**
     * 分页查询
     *
     * @param myPage 传入第几页、每页大小
     * @return 一页数据
     */
    public Map<String, Object> selectSongMap(MyPage myPage) {
        //1.创建分页对象
        //参数1代表第几页 参数2代表每页显示多少行
        //select*from food where like 起始行数：0,查询行数：2;
        //（页数-1）*行数 = 查询的起始行数
        Page<Song> page = new Page<>(myPage.getPage(), myPage.getSize());
        Page<Song> page1 = songDao.selectPage(page, null);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", page1.getPages());
        map.put("row", page1.getRecords());
        return map;

    }
}
