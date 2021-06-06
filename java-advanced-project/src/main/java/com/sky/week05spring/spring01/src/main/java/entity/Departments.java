package entity;

public class Departments {
    int deptNo;
    String deptNames;

    public int getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(int deptNo) {
        this.deptNo = deptNo;
    }

    public String getDeptNames() {
        return deptNames;
    }

    public void setDeptNames(String deptNames) {
        this.deptNames = deptNames;
    }

    @Override
    public String toString() {
        return "Departments{" +
                "deptNo=" + deptNo +
                ", deptNames='" + deptNames + '\'' +
                '}';
    }
}
