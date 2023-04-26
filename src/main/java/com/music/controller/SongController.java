package com.music.controller;

import com.music.entity.MyLyric;
import com.music.entity.MyPage;
import com.music.entity.Song;
import com.music.service.SongService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@Api(tags = "歌曲")
@RequestMapping("/song")
public class SongController {
    @Resource
    private SongService songService;

    /**
     * 根据歌曲-歌手获取详细详细，包括图片
     * @param name 歌曲-歌手
     * @return map
     * @throws Exception e
     */
    @GetMapping("/getPic")
    public Map<String, Object> getPic(String  name) throws Exception{
       return songService.getPic(name);
    }

    /**
     * 根据歌手分页查询
     * @param myPage 传入第几页、每页大小
     * @param singer 歌手名
     * @return 一页数据
     */
    @Operation(summary = "根据歌手分页查询")
    @GetMapping("/selectSongsMap")
    public Map<String, Object> selectSongsMap(MyPage myPage, String singer) {
        return songService.selectSongsMap(myPage, singer);
    }

    /**
     * 查询所有专辑-分页
     * @param myPage 传入第几页、每页大小
     * @return 一页数据
     */
    @Operation(summary = "查询所有专辑-分页")
    @GetMapping("/selectAllAlbum")
    public Map<String,Object> selectAllAlbum(MyPage myPage){
        return songService.selectAllAlbum(myPage);
    }

    /**
     * 根据专辑分页查询歌曲
     * @param myPage 传入第几页、每页大小
     * @param album 专辑
     * @return 一页数据
     */
    @Operation(summary = "根据专辑分页查询歌曲")
    @GetMapping("/selectByAlbum")
    public Map<String, Object> selectByAlbum(MyPage myPage,String album) {
        return songService.selectByAlbum(myPage, album);
    }

    /**
     * 获取歌词lrc
     * @param name 歌曲
     * @return 歌词
     * @throws FileNotFoundException 1
     */
    @Operation(summary = "获取歌词lrc")
    @GetMapping("/getLrc")
    public List<MyLyric> getLrc(String name) throws FileNotFoundException {
        return songService.getLrc(name);
    }

    /**
     * 搜索功能
     * @param data 数据
     * @return list
     */
    @Operation(summary = "搜索功能")
    @GetMapping("/SelectByUser")
    public Set<Song> SelectByUser(String data){
        return songService.SelectByUser(data);
    }

    /**
     * 查询歌曲数量
     * @return int
     */
    @Operation(summary = "查询歌曲数量")
    @GetMapping("/selectSongNum")
    public int selectSongNum(){
        return songService.selectSongNum();
    }
    /**
     * 查询歌单数量
     * @return int
     */
    @Operation(summary = "查询歌单数量")
    @GetMapping("/selectAlbumNum")
    public int selectAlbumNum(){
        return songService.selectAlbumNum();
    }

    /**
     * 分页查询歌手
     * @param myPage 1
     * @return 1
     */
    @Operation(summary = "分页查询歌曲")
    @GetMapping("/selectSongMap")
    public Map<String,Object>  selectSongMap(MyPage myPage) {
        return songService.selectSongMap(myPage);
    }
}
