package com.music.methods;

import com.music.utils.CsvUtil;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.datatype.Artwork;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 创建专辑分类csv
 */
public class GetAlbum {
    public static void main(String[] args) throws CannotReadException, TagException, InvalidAudioFrameException, ReadOnlyFileException, IOException {
        String headDataStr = "id,album,create_time,update_time";
        String csvFile = "E:/yz-music/csv/album.csv";

        Set<String> hashSet =new HashSet<>();
        List<String> dataList = new ArrayList<>();
        String path = "D:/Music";        //要遍历的路径
        File file = new File(path);        //获取其file对象
        File[] fs = file.listFiles();    //遍历path下的文件和目录，放在File数组中
        assert fs != null;
        for (File f : fs) {
            if (f.getName().endsWith("flac")||file.getName().endsWith("mp3")) {

                AudioFile audio = AudioFileIO.read(f);
                Tag tag = audio.getTag();
                String Album = tag.getFirst(FieldKey.ALBUM);//专辑
                hashSet.add(Album);
                //保存图片
                Artwork artwork = tag.getFirstArtwork();
                if(artwork!=null){
                    ImageIO.write(artwork.getImage(), "jpg", new File("E:\\yz-music\\img\\Album\\"+Album+".jpg"));
                }

            }
        }
        int i =1;
        //迭代器遍历：
        for (String str : hashSet) {
            dataList.add(i+","+str+",2023-04-03 14:42:54,2023-04-03 14:42:54");
            i++;
        }
        CsvUtil.writeToCsv(headDataStr, dataList, csvFile, true);

    }
}
