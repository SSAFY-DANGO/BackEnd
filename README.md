
# 개요
혹시 집에 있는 냉장고에 어떤 물건이 들어있는지 전부 기억하고 계신가요?

어떤 사람은 냉장고에 우유를 넣어놓고 까먹어서 버린적도 있고, 혹은 신선칸 안쪽에 대파를 넣어놨다가 얼어버린 적도 있을 것 입니다.

저희 Dango 팀은 이러한 경험에 착안하여 냉장고 안쪽의 정보를 실시간으로 편하게 알 수 있으면 이런 낭비를 크게 줄일 수 있지 않을까? 라는 생각을 하게되었습니다.

이러한 생각에 착안하여 탄생한 서비스가 `내 손안의 냉장고` , <U>당</U>신의 냉장<U>고</U> **당고** 입니다.

백엔드 개발과 관련하여 상세한 내용은 다음 wiki를 참고해주세요 -> [wiki](https://github.com/SSAFY-DANGO/BackEnd/wiki)

# 주요기능

### 냉장고(신선칸) 식재료 추적

    - 카메라를 활용하여 식재료 입출입 모습을 촬영하고 촬영한 사진을 AI를 활용하여 식재료 분류
    - 식재료 출입/경과시간 제공
    - 오래된 식재료 알림 기능

### 냉장고 문열림/닫힘 실시간 알림 기능

    - 초음파 센서를 사용하여 문여닫힘 정보 수집 및 제공

### 식재료 영양 정보 제공

    - 식품의약안전체에서 제공하는 식품영양성분 데이터를 기반으로 하여 정보 제공
    - 탄수화물, 단백질, 지방
    - 칼로리, 설탕

# 화면소개

![image](https://github.com/SSAFY-DANGO/BackEnd/assets/42714724/82d08e64-b13f-45c2-abf2-71ca399b7fd8)
![image](https://github.com/SSAFY-DANGO/BackEnd/assets/42714724/e8d70ff5-495e-4fbe-b101-ec2cd131d66b)
![image](https://github.com/SSAFY-DANGO/BackEnd/assets/42714724/83b05b75-bd55-45ec-a1a6-abdb8b4ea957)


# 팀소개

|[신온유]([https://github.com/tlsdhsdb](https://github.com/tlsdhsdb))|[전경향]([https://github.com/HappyHyang](https://github.com/HappyHyang))|[유호영]([https://github.com/Monami123321](https://github.com/Monami123321))|[이정민]([https://github.com/dangalee](https://github.com/dangalee))|[전예은]([https://github.com/yeeunjeon789](https://github.com/yeeunjeon789))|
|---|---|---|---|---|
|<img style="width:100px" src = "https://avatars.githubusercontent.com/tlsdhsdb"/>|<img style="width:100px" src = "https://avatars.githubusercontent.com/HappyHyang"/>|<img style="width:100px" src = "https://avatars.githubusercontent.com/Monami123321"/>|<img style="width:100px" src = "https://avatars.githubusercontent.com/dangalee"/>|<img style="width:100px" src = "https://avatars.githubusercontent.com/yeeunjeon789"/>|
|백엔드,AI|백엔드,인프라,IoT|백엔드,인프라|프론트|프론트|

# 아키텍쳐

![DANGO drawio (1)](https://github.com/SSAFY-DANGO/BackEnd/assets/42714724/b97e3118-6d5b-4372-8f9d-564ceef5d18d)


# 기술스택

| IoT | 버전 |
| --- | --- |
| OS | Debian GNU/Linux 12 |
| Rapberry Pi | 4 Model B Rev 1.2 |
| Arduino | UNO R3 |
| Python | 3.11.2 |
| Kafka | 3.6.1 |

| Infra | 버전 |
| --- | --- |
| AWS EC2  | Ubuntu 20.04.6 LTS |
| Docker | 25.0.4 |
| Nginx | 1.25.4 |
| Jenkins | 2.440.1 |

| Back End | 버전 |
| --- | --- |
| Java | 17 (Zulu) |
| Spring Boot | 3.2.3 (Gradle) |
| Spring Security | 6 |
| MariaDB | 10.11.6 |
| JPA | 3.1.0 |
| IntelliJ | 2023.3.2 |
| Redis | 7.2.4 |
| Lombok | 1.18.22 |
| Springdoc | 2.0.2 |

| Front End | 버전 |
| --- | --- |
| Node.js  | v20.10.0 |
| React  | 18.2.0 |
| yarn  | 1.22.21 |
| tailwindCSS  | 3.4.1 |
| recoil | 0.7.7 |
| eslint  | ^8 |
| axios | 1.6.8 |
| react-router-dom | 6.22.3 |
| vite | 5.1.6 |



