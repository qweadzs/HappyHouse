<template>
  <div class="bv-example-row mt-3 text-center">
    <v-row class="bv-example-row mt-3 text-center">
      <v-col sm="12">
        <v-card>
          <v-card-title class="cyan darken-1">
            <span class="text-h5 white--text"
              >{{ userInfo.userid }}님의 회원 정보</span
            >
            <v-btn
              class="ma-1"
              color="grey"
              plain
              @click="moveUpdate(userInfo.userid)"
            >
              수정하기
            </v-btn>

            <!-- <v-btn
              class="ma-1"
              color="error"
              plain
              @click="removeUser(userInfo.userid)"
            >
              회원탈퇴
            </v-btn> -->
            <v-btn
              class="ma-1"
              color="error"
              plain
              @click="removeUser('userInfo.userid')"
            >
              회원탈퇴
            </v-btn>
            <v-spacer></v-spacer>
          </v-card-title>

          <v-list>
            <v-divider inset></v-divider>

            <v-list-item>
              <v-list-item-action>
                <v-icon> mdi-account</v-icon>
              </v-list-item-action>

              <v-list-item-content>
                <v-list-item-title>{{ userInfo.username }}</v-list-item-title>
              </v-list-item-content>
            </v-list-item>

            <v-divider inset></v-divider>

            <v-list-item>
              <v-list-item-action>
                <v-icon>mdi-email</v-icon>
              </v-list-item-action>

              <v-list-item-content>
                <v-list-item-title>{{ userInfo.email }}</v-list-item-title>
              </v-list-item-content>
            </v-list-item>

            <v-divider inset></v-divider>

            <v-list-item>
              <v-list-item-action>
                <v-icon>mdi-map-marker</v-icon>
              </v-list-item-action>

              <v-list-item-content>
                <v-list-item-title>{{ userInfo.joindate }}</v-list-item-title>
              </v-list-item-content>
            </v-list-item>
            <v-divider inset></v-divider>

            <v-divider inset></v-divider>

            <v-list-item class="mb-2">
              <v-list-item-action>
                <v-icon>mdi-thumb-up-outline</v-icon>
              </v-list-item-action>

              <v-list-item-content class="ml-5">
                <wish-list-row
                  v-for="(wishapt, index) in wishlist"
                  :key="index"
                  v-bind="wishapt"
                />
              </v-list-item-content>
            </v-list-item>
          </v-list>
        </v-card>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import WishListRow from "@/components/user/child/WishListRow";
import { listWish } from "@/api/member.js";

import { mapState, mapMutations } from "vuex";
import { deleteUser } from "@/api/member";

const memberStore = "memberStore";

export default {
  name: "MemberMyPage",
  components: {
    WishListRow,
  },

  data() {
    return {
      wishlist: [],
    };
  },
  created() {
    listWish(
      this.userInfo.userid,
      (response) => {
        this.wishlist = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    removeUser(data) {
      if (confirm("정말로 탈퇴하시겠습니까?")) {
        console.log(data);
        deleteUser(data, () => {
          this.SET_IS_LOGIN(false);
          this.SET_USER_INFO(null);
          sessionStorage.removeItem("access-token");
          alert("탈퇴가 완료되었습니다.");
          if (this.$route.path != "/") this.$router.push({ name: "Home" });
        });
      }
    },
    moveUpdate(data) {
      if (this.$route.path != "/")
        this.$router.push({
          name: "Update",
          params: { userid: data },
        });
    },
  },
};
</script>

<style></style>
