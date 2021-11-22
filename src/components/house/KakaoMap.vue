<template>
  <!-- <div>
    <div id="map"></div>
    <div class="button-group">
      <button @click="changeSize(0)">Hide</button>
      <button @click="changeSize(400)">show</button>
      <button @click="displayMarker(markerPositions1)">marker set 1</button>
      <button @click="displayMarker(markerPositions2)">marker set 2</button>
      <button @click="displayMarker([])">marker set 3 (empty)</button>
      <button @click="displayInfoWindow">infowindow</button>
    </div>
  </div> -->
  <div class="map_wrap">
    <div
      id="map"
      style="width: 100%; height: 100%; position: relative; overflow: hidden"
    ></div>
    <ul id="category">
      <li id="BK9" data-order="0">
        <span @click="clickToSearch('BK9')" class="category_bg bank"></span>
        은행
      </li>
      <li id="MT1" data-order="1">
        <span @click="clickToSearch('MT1')" class="category_bg mart"></span>
        마트
      </li>
      <li id="PM9" data-order="2">
        <span @click="clickToSearch('PM9')" class="category_bg pharmacy"></span>
        약국
      </li>
      <li id="OL7" data-order="3">
        <span @click="clickToSearch('OL7')" class="category_bg oil"></span>
        주유소
      </li>
      <li id="CE7" data-order="4">
        <span @click="clickToSearch('CE7')" class="category_bg cafe"></span>
        카페
      </li>
      <li id="CS2" data-order="5">
        <span @click="clickToSearch('CS2')" class="category_bg store"></span>
        편의점
      </li>
    </ul>
  </div>
</template>

<script>
import { mapState } from "vuex";
// import { mapGetters, mapState } from "vuex";
const houseStore = "houseStore";
export default {
  name: "KakaoMap",
  data() {
    return {
      map: null,
      ps: null, // 장소 검색 객체를 생성합니다
      markers: [],
      marker: {},
      infowindow: null,
      moveLatLon: null, // 지도 위치 옮기는 변수
      geocoder: null, // 주소-좌표 변환 객체
      address: "",
      currCategory: "",
      coo: null, // 누른곳의 좌표
    };
  },
  // props: {
  //   house: Object,
  // },
  watch: {
    house: function () {
      return this.convertToLoc(this.house);
    },
    // geocoder: function () {
    //   return this.convertToLoc(this.house);
    // },
  },
  computed: {
    ...mapState(houseStore, ["house"]),
    // ...mapGetters(houseStore, ["house"]),
    // ...mapGetters(houseStore, ["house"]),
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?appkey=915cffed372954b7b44804ed422b9cf0&libraries=services";
      //autoload=false&
      document.head.appendChild(script);
    }
  },
  methods: {
    initMap() {
      console.log("init");
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 5,
      };
      // const map = new kakao.maps.Map(container, options);
      this.map = new kakao.maps.Map(container, options);
      this.infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
    },
    // 카테고리별 검색을 합니다.
    clickToSearch(category) {
      this.currCategory = category;
      this.ps = new kakao.maps.services.Places(this.map);
      // 카테고리로 은행을 검색합니다

      this.ps.categorySearch(category, this.placesSearchCB, {
        useMapBounds: true,
      });
    },
    // 키워드 검색 완료 시 호출되는 콜백함수 입니다
    placesSearchCB(data, status) {
      if (status === kakao.maps.services.Status.OK) {
        var order = document
          .getElementById(this.currCategory)
          .getAttribute("data-order");
        // 출력 전 모든 마커 삭제
        this.setMarkers(null);
        // 클릭한 카테고리의 검색된 개수만큼
        for (var i = 0; i < data.length; i++) {
          this.displayMarker(data[i], order);
        }
      }
    },

    // 지도에 마커를 표시하는 함수입니다
    displayMarker(place, order) {
      //place 객체는 다음의 속성을 가진다.
      //       address_name: "대구 동구 신암동 821-24"
      // category_group_code: "OL7"
      // category_group_name: "주유소,충전소"
      // category_name: "교통,수송 > 자동차 > 주유,가스 > 주유소 > S-oil주유소"
      // distance: ""
      // id: "18259017"
      // phone: "053-953-5144"
      // place_name: "신암로주유소"
      // place_url: "http://place.map.kakao.com/18259017"
      // road_address_name: "대구 동구 신암로 117"
      // x: "128.61218847406008"
      // y: "35.880997076920536"

      // 각 메뉴에 맞는 아이콘 설정
      var imageSrc =
          "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png", // 마커 이미지 url, 스프라이트 이미지를 씁니다
        imageSize = new kakao.maps.Size(27, 28), // 마커 이미지의 크기
        imgOptions = {
          spriteSize: new kakao.maps.Size(72, 208), // 스프라이트 이미지의 크기
          spriteOrigin: new kakao.maps.Point(46, order * 36), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
          offset: new kakao.maps.Point(11, 28), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
        },
        markerImage = new kakao.maps.MarkerImage(
          imageSrc,
          imageSize,
          imgOptions
        );
      // 마커를 생성하고 지도에 표시합니다
      var marker = new kakao.maps.Marker({
        map: this.map,
        position: new kakao.maps.LatLng(place.y, place.x),
        image: markerImage,
      });
      this.markers.push(marker);

      // 마커에 클릭이벤트를 등록합니다
      kakao.maps.event.addListener(marker, "click", function () {
        // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
        this.infowindow.setContent(
          '<div style="padding:5px;font-size:12px;">' +
            place.place_name +
            "</div>"
        );
        this.infowindow.open(this.map, marker);
      });
    },

    // 지도 위치 옮기기 - 파라미터로 옮기는게 나을거같음
    // moveTo() {
    //   this.map.panTo(this.moveLatLon);
    // },
    convertToLoc() {
      this.address = this.house.법정동 + " " + this.house.지번;
      this.geocoder = new kakao.maps.services.Geocoder();
      this.geocoder.addressSearch(this.address, (result, status) => {
        // 정상적으로 검색이 완료됐으면
        if (status === kakao.maps.services.Status.OK) {
          var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
          // 마커,윈도우 초기화
          this.setMarkers(null);
          this.infowindow.close();
          // 결과값으로 받은 위치를 마커로 표시합니다
          var marker = new kakao.maps.Marker({
            map: this.map,
            position: coords,
          });
          this.markers.push(marker);
          this.marker = marker;
          this.coo = coords;
          // 인포윈도우로 장소에 대한 설명을 표시합니다
          var infowindow = new kakao.maps.InfoWindow({
            content: `<div style="width:150px;text-align:center;padding:6px 0;">${this.house.아파트}</div>`,
          });
          infowindow.open(this.map, marker);
          this.infowindow = infowindow;
          // this.markers.push(marker);
          // this.infowindows.push(infowindow);
          this.ps = new kakao.maps.services.Places(this.map);
          // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
          this.map.panTo(coords);
        } else {
          console.log("실패");
        }
      });
    },
    // 배열에 추가된 마커들을 지도에 표시하거나 삭제하는 함수입니다
    setMarkers(map) {
      for (var i = 0; i < this.markers.length; i++) {
        this.markers[i].setMap(map);
      }
      if (map === null) {
        this.markers = [];
      }
    },
    // 배열에 추가된 마커들을 지도에 표시하거나 삭제하는 함수입니다
    setWindows(map, marker) {
      for (var i = 0; i < this.infowindows.length; i++) {
        this.infowindows[i].close(map, marker);
      }
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.map_wrap,
.map_wrap * {
  margin: 0;
  padding: 0;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  font-size: 12px;
}
.map_wrap {
  position: relative;
  width: 100%;
  height: 350px;
}
#category {
  position: absolute;
  top: 10px;
  left: 10px;
  border-radius: 5px;
  border: 1px solid #909090;
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.4);
  background: #fff;
  overflow: hidden;
  z-index: 2;
}
#category li {
  float: left;
  list-style: none;
  width: 50px;
  border-right: 1px solid #acacac;
  padding: 6px 0;
  text-align: center;
  cursor: pointer;
}
#category li.on {
  background: #eee;
}
#category li:hover {
  background: #ffe6e6;
  border-left: 1px solid #acacac;
  margin-left: -1px;
}
#category li:last-child {
  margin-right: 0;
  border-right: 0;
}
#category li span {
  display: block;
  margin: 0 auto 3px;
  width: 27px;
  height: 28px;
}
#category li .category_bg {
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png)
    no-repeat;
}
#category li .bank {
  background-position: -10px 0;
}
#category li .mart {
  background-position: -10px -36px;
}
#category li .pharmacy {
  background-position: -10px -72px;
}
#category li .oil {
  background-position: -10px -108px;
}
#category li .cafe {
  background-position: -10px -144px;
}
#category li .store {
  background-position: -10px -180px;
}
#category li.on .category_bg {
  background-position-x: -46px;
}
.placeinfo_wrap {
  position: absolute;
  bottom: 28px;
  left: -150px;
  width: 300px;
}
.placeinfo {
  position: relative;
  width: 100%;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  padding-bottom: 10px;
  background: #fff;
}
.placeinfo:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.placeinfo_wrap .after {
  content: "";
  position: relative;
  margin-left: -12px;
  left: 50%;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
.placeinfo a,
.placeinfo a:hover,
.placeinfo a:active {
  color: #fff;
  text-decoration: none;
}
.placeinfo a,
.placeinfo span {
  display: block;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}
.placeinfo span {
  margin: 5px 5px 0 5px;
  cursor: default;
  font-size: 13px;
}
.placeinfo .title {
  font-weight: bold;
  font-size: 14px;
  border-radius: 6px 6px 0 0;
  margin: -1px -1px 0 -1px;
  padding: 10px;
  color: #fff;
  background: #d95050;
  background: #d95050
    url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png)
    no-repeat right 14px center;
}
.placeinfo .tel {
  color: #0f7833;
}
.placeinfo .jibun {
  color: #999;
  font-size: 11px;
  margin-top: 0;
}
</style>
