# 니가 사는 그집
- 공공데이터를 활용해 웹 사이트로 전국 아파트, 전셋집의 매매가를 확인할 수 있는 웹 서비스.

# 기술 스택
- front-end
  - vue
  - vuex
- back-end
  - spring boot
  - MySql
  - mybatis
  - Maven
- ETC
  - kakao map
  - Chart.js

## Project setup
### front

```
npm install
```
### back in STS
![image](https://user-images.githubusercontent.com/62179139/157039931-239ff546-faf4-4f03-80a5-1fdc0348c8f0.png)

## Compiles and hot-reloads for development
### front
```
npm run serve
```

----------------------------
# Main Function
- 마이페이지
  - 내가 찜한 매물, 회원 정보 수정을 할 수 있다.
![image](https://user-images.githubusercontent.com/62179139/157041912-de453c15-29e1-4b76-be33-bf02b09385f9.png)

- 게시판
  - 글을 등록, 수정, 삭제할 수 있으며 게시글 20개 단위로 무한스크롤로 구현하였다.
![image](https://user-images.githubusercontent.com/62179139/157042098-f3247b1c-6684-4882-a1e0-acc5f8375f6f.png)
  - 게시글마다 댓글을 등록, 수정, 삭제할 수 있다.
![image](https://user-images.githubusercontent.com/62179139/157042368-384d6df5-4ddd-485e-a9c9-e91590f9faa4.png)

- 아파트 정보 조회
  - 매물을 시/도, 구/군, 동별로 검색 할 수 있으며 해당 매물 반경 500m안의 상가 정보를 확인 및 차트화하였다.
  - 매물 주변 산책로를 확인할 수 있다.
  - 매물을 찜하여 마이페이지에서 확인할 수 있다.
![image](https://user-images.githubusercontent.com/62179139/157042708-6b5bb20d-cbfd-48b0-88d8-2afcc6c15018.png)
