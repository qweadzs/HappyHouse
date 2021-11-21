const kakaoStore = {
  namespaced: true,
  state: {
    map: null,
    markers: [],
    moveLatLon: null, // 지도 위치 옮기는 변수
    geocoder: null, // 주소-좌표 변환 객체
    address: "",
  },
  getters: {},
  mutations: {
    SET_MAP(state, payload) {
      state.map = payload;
    },
  },
  actions: {
    convertToLoc(house) {
      this.address = house.법정동 + " " + house.지번;
      this.geocoder = new kakao.maps.services.Geocoder();
      this.geocoder.addressSearch(this.address, (result, status) => {
        // 정상적으로 검색이 완료됐으면
        if (status === kakao.maps.services.Status.OK) {
          var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
          console.log(coords);
          // 결과값으로 받은 위치를 마커로 표시합니다
          const marker = new kakao.maps.Marker({
            map: this.map,
            position: coords,
          });

          // 인포윈도우로 장소에 대한 설명을 표시합니다
          var infowindow = new kakao.maps.InfoWindow({
            content:
              '<div style="width:150px;text-align:center;padding:6px 0;">우리회사</div>',
          });
          infowindow.open(this.map, marker);

          // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
          // this.map.setCenter(coords);
          console.log(this.map);
          this.map.panTo(coords);
        } else {
          console.log("실패");
        }
      });
    },
  },
};

export default kakaoStore;
