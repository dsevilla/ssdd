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

	public Video(String id, String userid, PROCESS_STATUS pstatus, String date, String filename)
	{
		super();
		this.id = id;
		this.userid = userid;
		this.pstatus = pstatus;
		this.date = date;
		this.filename = filename;
	}

	/**
	 * @return the id
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id)
	{
		this.id = id;
	}

	/**
	 * @return the userid
	 */
	public String getUserid()
	{
		return userid;
	}

	/**
	 * @param userid the userid to set
	 */
	public void setUserid(String userid)
	{
		this.userid = userid;
	}

	/**
	 * @return the pstatus
	 */
	public PROCESS_STATUS getPstatus()
	{
		return pstatus;
	}

	/**
	 * @param pstatus the pstatus to set
	 */
	public void setPstatus(PROCESS_STATUS pstatus)
	{
		this.pstatus = pstatus;
	}

	/**
	 * @return the date
	 */
	public String getDate()
	{
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date)
	{
		this.date = date;
	}

	/**
	 * @return the filename
	 */
	public String getFilename()
	{
		return filename;
	}

	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename)
	{
		this.filename = filename;
	}
}