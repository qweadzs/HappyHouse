<template>
  <!-- <b-container class="bv-example-row mt-3 text-center">
    <h3 class="underline-hotpink">
      <b-icon icon="journals"></b-icon> Mail Service
    </h3>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left" @submit="onSubmit">
            <b-form-group label="제목:" label-for="mailname">
              <b-form-input
                id="mailname"
                v-model="mail.mailname"
                required
                placeholder="제목 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="내용:" label-for="mailcontent">
              <b-form-input
                id="mailcontent"
                v-model="mail.mailcontent"
                required
                placeholder="내용 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-button type="submit" variant="success" class="m-1"
              >메일보내기</b-button
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
  </b-container> -->

  <v-container class="bv-example-row mt-3 text-center">
    <h3>
      <div class="container">
        <div class="masthead-subheading">Mail Service</div>
      </div>
    </h3>
    <v-row>
      <v-col></v-col>
      <v-col cols="8">
        <v-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <v-form class="text-left" @submit="onSubmit">
            <v-form-group label="제목:" label-for="mailname">
              <v-text-field
                id="mailname"
                v-model="mail.mailname"
                required
                label="제목"
                @keyup.enter="confirm"
              ></v-text-field>
            </v-form-group>
            <v-form-group label="내용:" label-for="mailcontent">
              <v-textarea
                id="mailcontent"
                v-model="mail.mailcontent"
                outlined
                required
                label="내용"
                @keyup.enter="confirm"
              ></v-textarea>
            </v-form-group>
            <v-btn type="submit" variant="success" class="m-1"
              >메일보내기</v-btn
            >
            <v-btn type="button" variant="primary" class="m-1" @click="cancel"
              >취소</v-btn
            >
          </v-form>
        </v-card>
      </v-col>
      <v-col></v-col>
    </v-row>
  </v-container>
</template>

<script>
import { mailWrite } from "@/api/mail";
export default {
  name: "Mail",
  data() {
    return {
      mail: {
        mailname: null,
        mailcontent: null,
      },
    };
  },
  computed: {},
  methods: {
    onSubmit(event) {
      event.preventDefault();
      let err = true;
      let msg = "";
      !this.mail.mailname &&
        ((msg = "제목을 입력해주세요"),
        (err = false),
        this.$refs.mailname.focus()),
        err &&
          !this.mail.mailcontent &&
          ((msg = "내용을 입력해주세요"),
          (err = false),
          this.$refs.mailcontent.focus());

      if (!err) alert(msg);
      else this.mailRegist();
    },
    mailRegist() {
      mailWrite(
        {
          mailname: this.mail.mailname,
          mailcontent: this.mail.mailcontent,
        },
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.movepage();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    cancel() {
      this.$router.push({ name: "Home" });
    },
    movepage() {
      this.$router.push({ name: "Home" });
    },
  },
};
</script>

<style scoped>
.underline-green {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(56, 233, 40, 0.3) 30%
  );
}
.masthead-subheading {
  font-size: 50px;
  font-style: italic;
  line-height: 1.5rem;
  margin-bottom: 25px;
  font-family: "Roboto Slab", -apple-system, BlinkMacSystemFont, "Segoe UI",
    Roboto, "Helvetica Neue", Arial, sans-serif, "Apple Color Emoji",
    "Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji";
}
</style>
