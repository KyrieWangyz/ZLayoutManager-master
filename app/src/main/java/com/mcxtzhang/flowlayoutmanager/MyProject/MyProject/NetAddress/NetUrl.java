package com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.NetAddress;

/**
 * Created by Administrator on 2017/12/20 0020.
 */

public class NetUrl {//此方法用于存储各种接口地址
    public  static final String Recyclerciewurl="http://119.29.245.39/foodApp/interface/getArticalByDate.php";//测试时候的第一个地址
    public static final String COACHRESGISTER="http://123.207.46.140:8080/facePot/test/insertCoach.do?";//该接口用于教练注册自己的信息
    public  static final String AddVip="http://123.207.46.140:8080/facePot/test/insertMember.do?";//该接口用于增加会员
    public static final String ADDCOACH="";//暂时还没有这个接口
    public static final String TRAINMESSAGE="http://123.207.46.140:8080/facePot/test/selectAllTrain.do";//获取训练栏信息
    public static final String ALLCOACH="http://123.207.46.140:8080/facePot/test/selectCoach.do";//查找所有教练的信息
    public static final String VIPAPPCOACH="http://123.207.46.140:8080/facePot/test/insertApp.do?";//该接口会员预约教练
    public static final String ADDTRAIN="http://123.207.46.140:8080/facePot/test/insertTrain.do?";//用来添加训练栏
    public static final String ADDTONIC="http://123.207.46.140:8080/facePot/test/insertTonic.do?";//添加补剂
    public static final String SEARCHFORAPPOINT="http://123.207.46.140:8080/facePot/test/accordCoach.do?";//查找指定时间内可以接受预约的教练
    public static final String SEARCHAPPOINTBYCOACH="http://123.207.46.140:8080/facePot/test/selectByCoach.do?";//同过教练id查找预约情况
    public static final String SEARCHAPPOINTBYVIPID="http://123.207.46.140:8080/facePot/test/selectByMemberID.do?";//通过学员id查找预约情况
    public static final String CANCELAPPOINTMENT="http://123.207.46.140:8080/facePot/test/deleteApp.do?";//该接口用于取消预约

}
