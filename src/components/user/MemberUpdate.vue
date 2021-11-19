<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>회원정보 수정</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left" @submit="onSubmit">
            <b-form-group label="아이디:" label-for="userid">
              <b-form-input
                id="userid"
                v-model="user.userid"
                required
                placeholder="아이디 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="이름:" label-for="username">
              <b-form-input
                id="username"
                v-model="user.username"
                required
                placeholder="이름 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호:" label-for="userpwd">
              <b-form-input
                type="password"
                id="userpwd"
                v-model="user.userpwd"
                required
                placeholder="비밀번호 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="E-Mail:" label-for="email">
              <b-form-input
                type="email"
                id="email"
                v-model="user.email"
                required
                placeholder="E-Mail 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-button
              type="submit"
              variant="success"
              class="m-1"
              @click="updateUser"
              >수정</b-button
            >
            <b-button
              type="button"
              variant="primary"
              class="m-1"
              @click="cancel"
              >취소</b-button
            >
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { getUser, modifyUser } from "@/api/member";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "MemberUpdate",
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  data() {
    return {
      user: {
        userid: "",
        username: "",
        userpwd: "",
        email: "",
        joindate: "",
      },
    };
  },
  created() {
    console.log(this.userInfo.userid + "id print");
    getUser(
      this.userInfo.userid,
      ({ data }) => {
        this.user = data;
        // console.log(data + "회원가입 데이터");
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();
      let err = true;
      let msg = "";
      !this.user.userid &&
        ((msg = "아이디 입력해주세요"),
        (err = false),
        this.$refs.userid.focus()),
        err &&
          !this.user.username &&
          ((msg = "이름을 입력해주세요"),
          (err = false),
          this.$refs.username.focus());
      err &&
        !this.user.userpwd &&
        ((msg = "비밀번호 입력해주세요"),
        (err = false),
        this.$refs.userpwd.focus()),
        err &&
          !this.user.email &&
          ((msg = "email 입력해주세요"),
          (err = false),
          this.$refs.email.focus());

      if (!err) alert(msg);
      else this.updateUser();
    },
    updateUser() {
      // console.log(this.userInfo.userid + "param값");
      modifyUser(
        {
          userid: this.userInfo.userid,
          username: this.user.username,
          userpwd: this.user.userpwd,
          email: this.user.email,
          joindate: this.user.joindate,
        },
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    moveList() {
      this.$router.push({ name: "Home" });
    },
    cancel() {
      this.$router.push({ name: "MyPage" });
    },
  },
};
</script>

<style></style>
