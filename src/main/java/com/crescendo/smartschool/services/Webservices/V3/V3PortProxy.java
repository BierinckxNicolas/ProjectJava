package com.crescendo.smartschool.services.Webservices.V3;

import org.apache.commons.configuration.ConfigurationException;

public class V3PortProxy implements V3Port {
  private String _endpoint = null;
  private V3Port v3Port = null;

  public V3PortProxy() throws ConfigurationException {
    _initV3PortProxy();
  }

  public V3PortProxy(String endpoint) throws ConfigurationException {
    _endpoint = endpoint;
    _initV3PortProxy();
  }

  private void _initV3PortProxy() throws ConfigurationException {
    try {
      v3Port = (new com.crescendo.smartschool.services.Webservices.V3.V3ServiceLocator()).getV3Port();
      if (v3Port != null) {
        if (_endpoint != null){
          ((javax.xml.rpc.Stub)v3Port)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        }
        else {
          _endpoint = (String)((javax.xml.rpc.Stub)v3Port)._getProperty("javax.xml.rpc.service.endpoint.address");
        }
      }

    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }

  public String getEndpoint() {
    return _endpoint;
  }

  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (v3Port != null)
      ((javax.xml.rpc.Stub)v3Port)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);

  }

  public V3Port getV3Port() throws ConfigurationException {
    if (v3Port == null)
      _initV3PortProxy();
    return v3Port;
  }

  public Object saveUser(String accesscode, String internnumber, String username, String passwd1, String passwd2, String passwd3, String name, String surname, String extranames, String initials, String sex, String birthday, String birthplace, String birthcountry, String address, String postalcode, String location, String country, String email, String mobilephone, String homephone, String fax, String prn, String stamboeknummer, String basisrol, String untis) throws java.rmi.RemoteException, ConfigurationException {
    if (v3Port == null)
      _initV3PortProxy();
    return v3Port.saveUser(accesscode, internnumber, username, passwd1, passwd2, passwd3, name, surname, extranames, initials, sex, birthday, birthplace, birthcountry, address, postalcode, location, country, email, mobilephone, homephone, fax, prn, stamboeknummer, basisrol, untis);
  }

  public Object saveClass(String accesscode, String name, String desc, String code, String parent, String untis, String instituteNumber, String adminNumber, String schoolYearDate) throws java.rmi.RemoteException, ConfigurationException {
    if (v3Port == null)
      _initV3PortProxy();
    return v3Port.saveClass(accesscode, name, desc, code, parent, untis, instituteNumber, adminNumber, schoolYearDate);
  }

  public Object saveGroup(String accesscode, String name, String desc, String code, String parent, String untis) throws java.rmi.RemoteException, ConfigurationException {
    if (v3Port == null)
      _initV3PortProxy();
    return v3Port.saveGroup(accesscode, name, desc, code, parent, untis);
  }

  public Object getAllAccounts(String accesscode, String code, String recursive) throws java.rmi.RemoteException, ConfigurationException {
    if (v3Port == null)
      _initV3PortProxy();
    return v3Port.getAllAccounts(accesscode, code, recursive);
  }

  public Object getAllAccountsExtended(String accesscode, String code, String recursive) throws java.rmi.RemoteException, ConfigurationException {
    if (v3Port == null)
      _initV3PortProxy();
    return v3Port.getAllAccountsExtended(accesscode, code, recursive);
  }

  public Object getAllGroupsAndClasses(String accesscode) throws java.rmi.RemoteException, ConfigurationException {
    if (v3Port == null)
      _initV3PortProxy();
    return v3Port.getAllGroupsAndClasses(accesscode);
  }

  public String addCourse(String accesscode, String coursename, String coursedesc) throws java.rmi.RemoteException, ConfigurationException {
    if (v3Port == null)
      _initV3PortProxy();
    return v3Port.addCourse(accesscode, coursename, coursedesc);
  }

  public Object addCourseTeacher(String accesscode, String coursename, String coursedesc, String internnummer, String userlist) throws java.rmi.RemoteException, ConfigurationException {
    if (v3Port == null)
      _initV3PortProxy();
    return v3Port.addCourseTeacher(accesscode, coursename, coursedesc, internnummer, userlist);
  }

  public Object addCourseStudents(String accesscode, String coursename, String coursedesc, String groupIds) throws java.rmi.RemoteException, ConfigurationException {
    if (v3Port == null)
      _initV3PortProxy();
    return v3Port.addCourseStudents(accesscode, coursename, coursedesc, groupIds);
  }

  public String getCourses(String accesscode) throws java.rmi.RemoteException, ConfigurationException {
    if (v3Port == null)
      _initV3PortProxy();
    return v3Port.getCourses(accesscode);
  }

  public Object delUser(String accesscode, String userIdentifier, String officialDate) throws java.rmi.RemoteException, ConfigurationException {
    if (v3Port == null)
      _initV3PortProxy();
    return v3Port.delUser(accesscode, userIdentifier, officialDate);
  }

  public Object saveUserParameter(String accesscode, String userIdentifier, String paramName, String paramValue) throws java.rmi.RemoteException, ConfigurationException {
    if (v3Port == null)
      _initV3PortProxy();
    return v3Port.saveUserParameter(accesscode, userIdentifier, paramName, paramValue);
  }

  public String getClassList(Object accesscode) throws java.rmi.RemoteException, ConfigurationException {
    if (v3Port == null)
      _initV3PortProxy();
    return v3Port.getClassList(accesscode);
  }

  public String getClassListJson(Object accesscode) throws java.rmi.RemoteException, ConfigurationException {
    if (v3Port == null)
      _initV3PortProxy();
    return v3Port.getClassListJson(accesscode);
  }

  public Object saveClassList(String accesscode, String serializedList) throws java.rmi.RemoteException, ConfigurationException {
    if (v3Port == null)
      _initV3PortProxy();
    return v3Port.saveClassList(accesscode, serializedList);
  }

  public Object saveClassListJson(String accesscode, String jsonList) throws java.rmi.RemoteException, ConfigurationException {
    if (v3Port == null)
      _initV3PortProxy();
    return v3Port.saveClassListJson(accesscode, jsonList);
  }

  public Object delClass(String accesscode, String code) throws java.rmi.RemoteException, ConfigurationException {
    if (v3Port == null)
      _initV3PortProxy();
    return v3Port.delClass(accesscode, code);
  }

  public Object saveUserToClasses(String accesscode, String userIdentifier, String csvList) throws java.rmi.RemoteException, ConfigurationException {
    if (v3Port == null)
      _initV3PortProxy();
    return v3Port.saveUserToClasses(accesscode, userIdentifier, csvList);
  }

  public Object removeUserFromGroup(String accesscode, String userIdentifier, String _class, String officialDate) throws java.rmi.RemoteException, ConfigurationException {
    if (v3Port == null)
      _initV3PortProxy();
    return v3Port.removeUserFromGroup(accesscode, userIdentifier, _class, officialDate);
  }

  public Object saveUserToClass(String accesscode, String userIdentifier, String _class, String officialDate) throws java.rmi.RemoteException, ConfigurationException {
    if (v3Port == null)
      _initV3PortProxy();
    return v3Port.saveUserToClass(accesscode, userIdentifier, _class, officialDate);
  }

  public Object saveUserToClassesAndGroups(String accesscode, String userIdentifier, String csvList, int keepOld) throws java.rmi.RemoteException, ConfigurationException {
    if (v3Port == null)
      _initV3PortProxy();
    return v3Port.saveUserToClassesAndGroups(accesscode, userIdentifier, csvList, keepOld);
  }

  public Object setAccountStatus(String accesscode, String userIdentifier, Object accountStatus) throws java.rmi.RemoteException, ConfigurationException {
    if (v3Port == null)
      _initV3PortProxy();
    return v3Port.setAccountStatus(accesscode, userIdentifier, accountStatus);
  }

  public Object setAccountPhoto(String accesscode, String userIdentifier, String photo) throws java.rmi.RemoteException, ConfigurationException {
    if (v3Port == null)
      _initV3PortProxy();
    return v3Port.setAccountPhoto(accesscode, userIdentifier, photo);
  }

  public String getAccountPhoto(String accesscode, String userIdentifier) throws java.rmi.RemoteException, ConfigurationException {
    if (v3Port == null)
      _initV3PortProxy();
    return v3Port.getAccountPhoto(accesscode, userIdentifier);
  }

  public Object replaceInum(String accesscode, String oldInum, String newInum) throws java.rmi.RemoteException, ConfigurationException {
    if (v3Port == null)
      _initV3PortProxy();
    return v3Port.replaceInum(accesscode, oldInum, newInum);
  }

  public Object forcePasswordReset(String accesscode, String userIdentifier, int accountType) throws java.rmi.RemoteException, ConfigurationException {
    if (v3Port == null)
      _initV3PortProxy();
    return v3Port.forcePasswordReset(accesscode, userIdentifier, accountType);
  }

  public Object savePassword(String accesscode, String userIdentifier, String password, int accountType) throws java.rmi.RemoteException, ConfigurationException {
    if (v3Port == null)
      _initV3PortProxy();
    return v3Port.savePassword(accesscode, userIdentifier, password, accountType);
  }

  public Object sendMsg(String accesscode, String userIdentifier, String title, String body, String senderIdentifier, Object attachments, int coaccount) throws java.rmi.RemoteException, ConfigurationException {
    if (v3Port == null)
      _initV3PortProxy();
    return v3Port.sendMsg(accesscode, userIdentifier, title, body, senderIdentifier, attachments, coaccount);
  }

  public Object getSkoreClassTeacherCourseRelation(String accesscode) throws java.rmi.RemoteException, ConfigurationException {
    if (v3Port == null)
      _initV3PortProxy();
    return v3Port.getSkoreClassTeacherCourseRelation(accesscode);
  }

  public Object unregisterStudent(String accesscode, String userIdentifier, String officialDate) throws java.rmi.RemoteException, ConfigurationException {
    if (v3Port == null)
      _initV3PortProxy();
    return v3Port.unregisterStudent(accesscode, userIdentifier, officialDate);
  }

  public Object getClassTeachers(String accesscode, boolean getAllOwners) throws java.rmi.RemoteException, ConfigurationException {
    if (v3Port == null)
      _initV3PortProxy();
    return v3Port.getClassTeachers(accesscode, getAllOwners);
  }

  public String getAbsentsByDate(String accesscode, String date) throws java.rmi.RemoteException, ConfigurationException {
    if (v3Port == null)
      _initV3PortProxy();
    return v3Port.getAbsentsByDate(accesscode, date);
  }

  public String getAbsents(String accesscode, String userIdentifier, String schoolYear) throws java.rmi.RemoteException, ConfigurationException {
    if (v3Port == null)
      _initV3PortProxy();
    return v3Port.getAbsents(accesscode, userIdentifier, schoolYear);
  }

  public String getUserDetails(String accesscode, String userIdentifier) throws java.rmi.RemoteException, ConfigurationException {
    if (v3Port == null)
      _initV3PortProxy();
    return v3Port.getUserDetails(accesscode, userIdentifier);
  }

  public String getUserDetailsByNumber(String accesscode, String number) throws java.rmi.RemoteException, ConfigurationException {
    if (v3Port == null)
      _initV3PortProxy();
    return v3Port.getUserDetailsByNumber(accesscode, number);
  }

  public String getUserDetailsByUsername(String accesscode, String username) throws java.rmi.RemoteException, ConfigurationException {
    if (v3Port == null)
      _initV3PortProxy();
    return v3Port.getUserDetailsByUsername(accesscode, username);
  }

  public String getUserOfficialClass(String accesscode, String userIdentifier, String date) throws java.rmi.RemoteException, ConfigurationException {
    if (v3Port == null)
      _initV3PortProxy();
    return v3Port.getUserOfficialClass(accesscode, userIdentifier, date);
  }

  public boolean saveSignature(String accesscode, String userIdentifier, int accountType, String signature) throws java.rmi.RemoteException, ConfigurationException {
    if (v3Port == null)
      _initV3PortProxy();
    return v3Port.saveSignature(accesscode, userIdentifier, accountType, signature);
  }

  public String getStudentCareer(String accesscode, String userIdentifier) throws java.rmi.RemoteException, ConfigurationException {
    if (v3Port == null)
      _initV3PortProxy();
    return v3Port.getStudentCareer(accesscode, userIdentifier);
  }

  public Object deactivateTwoFactorAuthentication(String accesscode, String userIdentifier, int accountType) throws java.rmi.RemoteException, ConfigurationException {
    if (v3Port == null)
      _initV3PortProxy();
    return v3Port.deactivateTwoFactorAuthentication(accesscode, userIdentifier, accountType);
  }

  public void returnErrorCodes() throws java.rmi.RemoteException, ConfigurationException {
    if (v3Port == null)
      _initV3PortProxy();
    v3Port.returnErrorCodes();
  }

  public String returnCsvErrorCodes() throws java.rmi.RemoteException, ConfigurationException {
    if (v3Port == null)
      _initV3PortProxy();
    return v3Port.returnCsvErrorCodes();
  }

  public String returnJsonErrorCodes() throws java.rmi.RemoteException, ConfigurationException {
    if (v3Port == null)
      _initV3PortProxy();
    return v3Port.returnJsonErrorCodes();
  }


}