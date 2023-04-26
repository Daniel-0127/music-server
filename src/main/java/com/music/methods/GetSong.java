package com.music.methods;

import com.music.utils.CsvUtil;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GetSong {
    public static void main(String[] args) throws Exception {
        String headDataStr = "id,name,singer,album,create_time,update_time";
        String csvFile = "E:/yz-music/csv/song.csv";

        int i =1;
        List<String> dataList = new ArrayList<>();
        String path = "D:/Music";        //要遍历的路径
        File file = new File(path);        //获取其file对象
        File[] fs = file.listFiles();    //遍历path下的文件和目录，放在File数组中
        assert fs != null;
        for (File f : fs) {
            if (f.getName().endsWith("flac")||file.getName().endsWith("mp3")) {

                AudioFile audio = AudioFileIO.read(f);
                Tag tag = audio.getTag();

                String Title = tag.getFirst(FieldKey.TITLE);//歌名
                String Artist = tag.getFirst(FieldKey.ARTIST);//歌手
                String Album = tag.getFirst(FieldKey.ALBUM);//专辑
                dataList.add(i+","+Title+","+Artist+","+Album+",2023-03-27 16:53:54,2023-03-27 16:53:54");
                i++;
            }
        }
        CsvUtil.writeToCsv(headDataStr, dataList, csvFile, true);

    }
}
