# BoardProject
IDE : Intellij   
Springboot : 3.2.0    
DB : MariaDB  
JDK : 17    
View : Thymeleaf  


Dependency : JPA, Lombok, devtools, web, security, validation  

# 프로젝트 진행  
2024-01-17 ~ 2024-02-08
  
# ERD
![20240209_163816](https://github.com/yec3168/BusanTravel/assets/85733545/225af34e-fa90-413d-bbea-81501bd8e576)



# 🔎회원  
회원가입   /members/new  post, get   
로그인     /members/login  post, get  
로그아웃    /members/logout  get
회원탈퇴    /members/delete/{id}  get


# 👨🏻‍🤝‍👨🏻마이페이지  
나의정보   /members/memberInfo   get  
정보수정    /members/update/{id}  post, get  
이미지수정  /members/update/image/{id}  post, get  


# 자유게시판  
게시판목록  /board/list  get  
게시판등록  /board/new  post, get  
게시판상세설명  /board/detail/{id}  get
게시판수정  /board/update/{id}   post, get  
게시판삭제  /board/delete/{id]  get  
  
  
# 댓글등록  
댓글생성  /board/answer/create/{id}  post



