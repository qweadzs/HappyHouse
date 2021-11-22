<template>
  <div class="regist">
    <div v-if="modifyComment != null" class="regist_form">
      <v-textarea
        name="comment"
        id="comment"
        outlined
        v-model="modifyComment.comment"
        cols="35"
        rows="2"
      ></v-textarea>
      <button class="small" @click="updateCommentCancel">취소</button>
      <button class="small" @click="updateComment">수정</button>
    </div>
    <div v-else class="regist_form">
      <v-text-field
        label="댓글"
        outlined
        name="comment"
        id="comment"
        v-model="comment"
        cols="12"
        sm="6"
        md="3"
        @keyup.enter="registComment"
      ></v-text-field>
      <button class="small" @click="registComment">등록</button>
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters, mapState } from "vuex";
const commentStore = "commentStore";
const memberStore = "memberStore";
export default {
  props: {
    modifyComment: Object,
  },
  computed: {
    ...mapGetters(commentStore, ["comments"]),
    ...mapState(commentStore, ["comments"]),
    ...mapState(memberStore, ["userInfo"]),
  },
  created: {},
  data() {
    return {
      // 차후 작성자 이름은 로그인 구현후 로그인한 사용자로 바꾼다.
      user_name: "",
      comment: "",
    };
  },
  methods: {
    ...mapActions(commentStore, ["regComment", "getComments", "updComment"]),
    registComment() {
      console.log(this.userInfo);
      this.regComment({
        user_name: this.userInfo.username,
        comment: this.comment,
        articleNo: this.$route.params.articleno,
      });
      this.comment = "";
      this.$router.go();
    },
    updateComment() {
      this.updComment(this.modifyComment);
      this.$router.go();
    },
    updateCommentCancel() {
      this.$emit("modify-comment-cancel", false);
    },
  },
};
</script>

<style scoped>
textarea {
  width: 90%;
  font-size: large;
  border: 1px;
}
button {
  float: right;
}
button.small {
  width: 60px;
  font-size: small;
  font-weight: bold;
}
</style>
