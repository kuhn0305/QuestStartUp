# QuestStartUp
 Launch Application on Oculus Quest Booting
 
## About
 '오큘러스 퀘스트2'가 부팅될 때, 사용자가 원하는 어플리케이션을 자동으로 실행해주는 프로그램입니다.  
 A program that automatically launch the specific application that the user wants when the 'Oculus Quest2' boots.

## System
 - Oculus Quest2
 - Android 10.0 (Q)
 - API Level 29

## How to Use
 1. APK 파일을 설치합니다.
 2. 아래 코드와 같이 자동 실행되기를 원하는 어플리케이션의 패키지 이름을 저장하는 json 파일을 생성합니다.
    ```
    {
      "package" : "com.target.packagename"
    }
    ```
 3. 해당 파일을 'sdcard/packagename.json'에 저장합니다.
 4. 'com.tektonspace.queststartup' 을 실행하고, 권한 요청을 수락합니다. (권한은 처음 실행할 때만 요청합니다.)
 5. 기기를 재부팅하고, 설정한 어플리케이션이 자동으로 실행되는지 확인합니다.
    
---
    
 1. Install the apk file.
 2. Create a json file that stores the package name of the application you want to auto-startup-run like below.
    ```
    {
      "package" : "com.target.packagename"
    }
    ```
3. Save the file to `scard/packagename.json` path of quest2 file system.
4. Run 'com.tektonspace.queststartup' and accept permission requests. (Permissions are requested only the first time you run.)
5. Reboot the HMD and verify that your application runs automatically.
