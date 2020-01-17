package com.his.entity;
/**
 * 病人表
 * @author 荣泽尚
 *
 */
import java.io.Serializable;
import java.util.Date;

public class Patient implements Serializable{
	
    @Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", patientCode=" + patientCode + ", patientPassword="
				+ patientPassword + ", patientName=" + patientName + ", patientSex=" + patientSex + ", patientAge="
				+ patientAge + ", patientTelephone=" + patientTelephone + ", patientIdentitycard=" + patientIdentitycard
				+ ", patientDepartmentId=" + patientDepartmentId + ", patientAdviceId=" + patientAdviceId
				+ ", patientPrescriptionCode=" + patientPrescriptionCode + ", patientHospitalizationId="
				+ patientHospitalizationId + ", patientCreatedate=" + patientCreatedate + "]";
	}

	private Integer patientId;//病人编号

    private String patientCode;//病人登录账号

    private String patientPassword;//登录的密码

    private String patientName;//病人姓名

    private String patientSex;//病人性别

    private Integer patientAge;//病人年龄

    private String patientTelephone;//病人联系电话

    private String patientIdentitycard;//病人身份证

    private Integer patientDepartmentId;//就诊科室编号

    private Integer patientAdviceId;//正在执行的医嘱

    private String patientPrescriptionCode;//病人要取的药方

    private Integer patientHospitalizationId;//住院情况

    private Date patientCreatedate;//注册时间
    
    private Hospitalization hospitalization;//住院信息的对象

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getPatientCode() {
        return patientCode;
    }

    public void setPatientCode(String patientCode) {
        this.patientCode = patientCode == null ? null : patientCode.trim();
    }

    public String getPatientPassword() {
        return patientPassword;
    }

    public void setPatientPassword(String patientPassword) {
        this.patientPassword = patientPassword == null ? null : patientPassword.trim();
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName == null ? null : patientName.trim();
    }

    public String getPatientSex() {
        return patientSex;
    }

    public void setPatientSex(String patientSex) {
        this.patientSex = patientSex == null ? null : patientSex.trim();
    }

    public Integer getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(Integer patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientTelephone() {
        return patientTelephone;
    }

    public void setPatientTelephone(String patientTelephone) {
        this.patientTelephone = patientTelephone == null ? null : patientTelephone.trim();
    }

    public String getPatientIdentitycard() {
        return patientIdentitycard;
    }

    public void setPatientIdentitycard(String patientIdentitycard) {
        this.patientIdentitycard = patientIdentitycard == null ? null : patientIdentitycard.trim();
    }

    public Integer getPatientDepartmentId() {
        return patientDepartmentId;
    }

    public void setPatientDepartmentId(Integer patientDepartmentId) {
        this.patientDepartmentId = patientDepartmentId;
    }

    public Integer getPatientAdviceId() {
        return patientAdviceId;
    }

    public void setPatientAdviceId(Integer patientAdviceId) {
        this.patientAdviceId = patientAdviceId;
    }

    public String getPatientPrescriptionCode() {
        return patientPrescriptionCode;
    }

    public void setPatientPrescriptionCode(String patientPrescriptionCode) {
        this.patientPrescriptionCode = patientPrescriptionCode == null ? null : patientPrescriptionCode.trim();
    }

    public Integer getPatientHospitalizationId() {
        return patientHospitalizationId;
    }

    public void setPatientHospitalizationId(Integer patientHospitalizationId) {
        this.patientHospitalizationId = patientHospitalizationId;
    }

    public Date getPatientCreatedate() {
        return patientCreatedate;
    }

    public void setPatientCreatedate(Date patientCreatedate) {
        this.patientCreatedate = patientCreatedate;
    }

	public Hospitalization getHospitalization() {
		return hospitalization;
	}

	public void setHospitalization(Hospitalization hospitalization) {
		this.hospitalization = hospitalization;
	}
    
}