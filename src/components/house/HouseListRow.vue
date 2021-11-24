<template>
  <v-row class="m-2 row" @click="selectHouse">
    <v-btn @click="goregist" fab dark small color="pink" class="wish-btn">
      <v-icon> mdi-heart </v-icon>
    </v-btn>
    <v-col cols="10" class="align-self-center">
      {{ house.아파트 }} [{{ house.도로명 }}] - {{ house.층 }}층
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
      aptCode: "Number",
      aptName: "",
      dongCode: "",
      dongName: "",
      buildYear: Number,
      jibun: "",
      dealAmount: "",
      dealYear: "",
      dealMonth: "",
      dealDay: "",
      area: "",
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
          dealYear: this.house.년,
          dealMonth: this.house.월,
          dealDay: this.house.일,
          area: this.house.전용면적,

          //일단 aptcode로 받아야하고
          // aptcode 없는건 우짤까
        },
        ({ data }) => {
          console.log(this.house);
          let msg = "";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
        },
        (error) => {
          let msg = "이미 등록된 위시리스트 입니다.";
          alert(msg);
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
.row :hover {
  background-color: lightblue;
  cursor: pointer;
  transition: 0.5s;
}
</style>
