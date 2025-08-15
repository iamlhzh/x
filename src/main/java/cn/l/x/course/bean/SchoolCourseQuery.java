package cn.l.x.course.bean;

public class SchoolCourseQuery {
	/**
	 *
	 */
	private String schoolId;
	/**
	 *课程状态:
	 * 10:正在进行
	 * 20:即将开始
	 * 30:全部
	 * 0:已经结束
	 */
	private Integer courseStatus;
	/**
	 *
	 */
	private Integer type;
	/**
	 *第几页
	 */
	private Integer p;
	/**
	 * 每页多少
	 */
	private Integer psize;
	/**
	 * 学校简称
	 */
	private String abbreviation;

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public Integer getCourseStatus() {
		return courseStatus;
	}

	public void setCourseStatus(Integer courseStatus) {
		this.courseStatus = courseStatus;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getP() {
		return p;
	}

	public void setP(Integer p) {
		this.p = p;
	}

	public Integer getPsize() {
		return psize;
	}

	public void setPsize(Integer psize) {
		this.psize = psize;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	@Override
	public String toString() {
		return "SchoolCourseQuery{" +
				"schoolId='" + schoolId + '\'' +
				", courseStatus=" + courseStatus +
				", type=" + type +
				", p=" + p +
				", psize=" + psize +
				", abbreviation='" + abbreviation + '\'' +
				'}';
	}
}
