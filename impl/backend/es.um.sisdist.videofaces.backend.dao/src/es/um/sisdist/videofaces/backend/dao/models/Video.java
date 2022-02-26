/**
 *
 */
package es.um.sisdist.videofaces.backend.dao.models;

public class Video
{
    private String id;
    private String userid;

    public enum PROCESS_STATUS
    {
        PROCESSING, PROCESSED
    }

    private PROCESS_STATUS pstatus;
    private String date;
    private String filename; // En caso de que se utilice un esquema híbrido de usar un sistema de ficheros

    // Note: blob data is not included

    public Video()
    {
    }
}