package es.um.sisdist.videofaces.backend.dao.video;

import java.io.InputStream;
import java.util.Optional;

import es.um.sisdist.videofaces.backend.dao.models.Video;

public interface IVideoDAO
{
    public Optional<Video> getVideoById(String id);

    // Get stream of video data
    public InputStream getStreamForVideo(String id);

    public Video.PROCESS_STATUS getVideoStatus(String id);
}
