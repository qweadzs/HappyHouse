<template>
  <v-row class="m-2" @click="selectHouse">
    <v-btn @click="goregist" fab dark small color="pink" class="wish-btn">
      <v-icon> mdi-heart </v-icon>
    </v-btn>
    <v-col cols="10" class="align-self-center">
      {{ house.아파트 }} [{{ house.도로명 }}]
    </v-col>
  </v-row>
</template>

<script>
import { mapState, mapActions } from "vuex";
import { wishregist } from "@/api/house";

const memberStore = "memberStore";
const houseStore = "houseStore";

export default {
  name: "HouseListRow",
  data() {
    return {
      isColor: false,
      userid: "",
      aptCode: Number,
      aptName: "",
      dongCode: "",
      dongName: "",
      buildYear: Number,
      jibun: "",
      lat: "",
      lng: "",
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  props: {
    house: Object,
  },
  methods: {
    ...mapActions(houseStore, ["detailHouse"]),
    selectHouse() {
      // console.log("listRow : ", this.house);
      // this.$store.dispatch("getHouse", this.house);
      this.detailHouse(this.house);
    },
    goregist() {
      wishregist(
        {
          userid: this.userInfo.userid,
          aptCode: this.house.일련번호,
          aptName: this.house.아파트,
          dongCode: this.house.법정동본번코드,
          dongName: this.house.법정동,
          buildYear: this.house.건축년도,
          jibun: this.house.지번,
          dealAmount: this.house.거래금액,
          //일단 aptcode로 받아야하고
          // aptcode 없는건 우짤까
        },
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        },
        (error) => {
          alert("아이디가 중복됩니다. 다른 아이디를 사용해주세요.");
          console.log(error);
        }
      );
    },
  },
};
</script>

<style scoped>
.apt {
  width: 50px;
}
</style>
