package model;
// Generated 2017/7/12 下午 05:20:31 by Hibernate Tools 5.2.3.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Process generated by hbm2java
 */
@Entity
@Table(name = "Process", catalog = "Meeting")
public class ProcessBean implements java.io.Serializable {

	private Integer id;
	private Integer process_id;
 	private Date replyDate;
	private String sponsor;
	private String status;
	private String presestation;
	private String reference;
	private String upload;

	public ProcessBean() {
	}

	public ProcessBean(Integer process_id, Date replyDate, String sponsor, String status, String presestation, String reference,
			String upload) {
		this.process_id = process_id;
 		this.replyDate = replyDate;
		this.sponsor = sponsor;
		this.status = status;
		this.presestation = presestation;
		this.reference = reference;
		this.upload = upload;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	 @Column(name = "process_id",unique = true, nullable = false)
	public Integer getProcess_id() {
		return process_id;
	}

	public void setProcess_id(Integer process_id) {
		this.process_id = process_id;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "reply_date", nullable = false, length = 10)
	public Date getReplyDate() {
		return this.replyDate;
	}

	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}

	@Column(name = "sponsor", nullable = false, length = 16)
	public String getSponsor() {
		return this.sponsor;
	}

	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

	@Column(name = "status", nullable = false, length = 3000)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "presestation", nullable = false, length = 30)
	public String getPresestation() {
		return this.presestation;
	}

	public void setPresestation(String presestation) {
		this.presestation = presestation;
	}

	@Column(name = "reference", nullable = false, length = 200)
	public String getReference() {
		return this.reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	@Column(name = "upload", nullable = false, length = 200)
	public String getUpload() {
		return this.upload;
	}

	public void setUpload(String upload) {
		this.upload = upload;
	}

}
