/**
 * V3Port.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.crescendo.smartschool.services.Webservices.V3;

import org.apache.commons.configuration.ConfigurationException;

public interface V3Port extends java.rmi.Remote {

    public Object saveUser(String accesscode, String internnumber, String username, String passwd1, String passwd2, String passwd3, String name, String surname, String extranames, String initials, String sex, String birthday, String birthplace, String birthcountry, String address, String postalcode, String location, String country, String email, String mobilephone, String homephone, String fax, String prn, String stamboeknummer, String basisrol, String untis) throws java.rmi.RemoteException, ConfigurationException;

    public Object saveClass(String accesscode, String name, String desc, String code, String parent, String untis, String instituteNumber, String adminNumber, String schoolYearDate) throws java.rmi.RemoteException, ConfigurationException;

    public Object saveGroup(String accesscode, String name, String desc, String code, String parent, String untis) throws java.rmi.RemoteException, ConfigurationException;

    public Object getAllAccounts(String accesscode, String code, String recursive) throws java.rmi.RemoteException, ConfigurationException;

    public Object getAllAccountsExtended(String accesscode, String code, String recursive) throws java.rmi.RemoteException, ConfigurationException;

    public Object getAllGroupsAndClasses(String accesscode) throws java.rmi.RemoteException, ConfigurationException;

    public String addCourse(String accesscode, String coursename, String coursedesc) throws java.rmi.RemoteException, ConfigurationException;

    public Object addCourseTeacher(String accesscode, String coursename, String coursedesc, String internnummer, String userlist) throws java.rmi.RemoteException, ConfigurationException;

    public Object addCourseStudents(String accesscode, String coursename, String coursedesc, String groupIds) throws java.rmi.RemoteException, ConfigurationException;

    public String getCourses(String accesscode) throws java.rmi.RemoteException, ConfigurationException;

    public Object delUser(String accesscode, String userIdentifier, String officialDate) throws java.rmi.RemoteException, ConfigurationException;

    public Object saveUserParameter(String accesscode, String userIdentifier, String paramName, String paramValue) throws java.rmi.RemoteException, ConfigurationException;

    public String getClassList(Object accesscode) throws java.rmi.RemoteException, ConfigurationException;

    public String getClassListJson(Object accesscode) throws java.rmi.RemoteException, ConfigurationException;

    public Object saveClassList(String accesscode, String serializedList) throws java.rmi.RemoteException, ConfigurationException;

    public Object saveClassListJson(String accesscode, String jsonList) throws java.rmi.RemoteException, ConfigurationException;

    public Object delClass(String accesscode, String code) throws java.rmi.RemoteException, ConfigurationException;

    public Object saveUserToClasses(String accesscode, String userIdentifier, String csvList) throws java.rmi.RemoteException, ConfigurationException;

    public Object removeUserFromGroup(String accesscode, String userIdentifier, String _class, String officialDate) throws java.rmi.RemoteException, ConfigurationException;

    public Object saveUserToClass(String accesscode, String userIdentifier, String _class, String officialDate) throws java.rmi.RemoteException, ConfigurationException;

    public Object saveUserToClassesAndGroups(String accesscode, String userIdentifier, String csvList, int keepOld) throws java.rmi.RemoteException, ConfigurationException;

    public Object setAccountStatus(String accesscode, String userIdentifier, Object accountStatus) throws java.rmi.RemoteException, ConfigurationException;

    public Object setAccountPhoto(String accesscode, String userIdentifier, String photo) throws java.rmi.RemoteException, ConfigurationException;

    public String getAccountPhoto(String accesscode, String userIdentifier) throws java.rmi.RemoteException, ConfigurationException;

    public Object replaceInum(String accesscode, String oldInum, String newInum) throws java.rmi.RemoteException, ConfigurationException;

    public Object forcePasswordReset(String accesscode, String userIdentifier, int accountType) throws java.rmi.RemoteException, ConfigurationException;

    public Object savePassword(String accesscode, String userIdentifier, String password, int accountType) throws java.rmi.RemoteException, ConfigurationException;

    public Object sendMsg(String accesscode, String userIdentifier, String title, String body, String senderIdentifier, Object attachments, int coaccount) throws java.rmi.RemoteException, ConfigurationException;

    public Object getSkoreClassTeacherCourseRelation(String accesscode) throws java.rmi.RemoteException, ConfigurationException;

    public Object unregisterStudent(String accesscode, String userIdentifier, String officialDate) throws java.rmi.RemoteException, ConfigurationException;

    public Object getClassTeachers(String accesscode, boolean getAllOwners) throws java.rmi.RemoteException, ConfigurationException;

    public String getAbsentsByDate(String accesscode, String date) throws java.rmi.RemoteException, ConfigurationException;

    public String getAbsents(String accesscode, String userIdentifier, String schoolYear) throws java.rmi.RemoteException, ConfigurationException;

    public String getUserDetails(String accesscode, String userIdentifier) throws java.rmi.RemoteException, ConfigurationException;

    public String getUserDetailsByNumber(String accesscode, String number) throws java.rmi.RemoteException, ConfigurationException;

    public String getUserDetailsByUsername(String accesscode, String username) throws java.rmi.RemoteException, ConfigurationException;

    public String getUserOfficialClass(String accesscode, String userIdentifier, String date) throws java.rmi.RemoteException, ConfigurationException;

    public boolean saveSignature(String accesscode, String userIdentifier, int accountType, String signature) throws java.rmi.RemoteException, ConfigurationException;

    public String getStudentCareer(String accesscode, String userIdentifier) throws java.rmi.RemoteException, ConfigurationException;

    public Object deactivateTwoFactorAuthentication(String accesscode, String userIdentifier, int accountType) throws java.rmi.RemoteException, ConfigurationException;

    public void returnErrorCodes() throws java.rmi.RemoteException, ConfigurationException;

    public String returnCsvErrorCodes() throws java.rmi.RemoteException, ConfigurationException;

    public String returnJsonErrorCodes() throws java.rmi.RemoteException, ConfigurationException;
}
