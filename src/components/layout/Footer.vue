<template>
  <v-bottom-navigation app v-model="value">
    <v-btn>
      <span>구독하기</span>
      <v-icon @click="doSubscribe()">mdi-heart</v-icon>
    </v-btn>
  </v-bottom-navigation>
  <!-- </v-footer> -->
</template>

<script>
import { mapState } from "vuex";
import { registSub } from "@/api/member";
const memberStore = "memberStore";

export default {
  name: "FooterBar",
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  data: () => ({
    links: ["Home", "About Us", "Team", "Services", "Blog", "Contact Us"],
    value: 1,
    active: false,
  }),
  methods: {
    doSubscribe() {
      if (this.isLogin) {
        registSub(
          this.userInfo.userid,
          ({ data }) => {
            let msg = "등록 처리시 문제가 발생했습니다.";
            if (data === "success") {
              msg = "등록이 완료되었습니다.";
            }
            alert(msg);
          },
          (error) => {
            alert("이미 구독 중 이십니다");
            console.log(error);
          }
        );
      } else {
        alert("로그인을 해주세요 !!");
      }
    },
  },
};
</script>
<style>
.footer {
  position: sticky;
  bottom: 0;
}
</style>
