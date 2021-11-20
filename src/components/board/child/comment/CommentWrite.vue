<template>
  <div class="regist">
    <div v-if="modifyComment != null" class="regist_form">
      <textarea
        name="comment"
        id="comment"
        v-model="modifyComment.comment"
        cols="35"
        rows="2"
      ></textarea>
      <button class="small" @click="updateCommentCancel">취소</button>
      <button class="small" @click="updateComment">수정</button>
    </div>
    <div v-else class="regist_form">
      <textarea
        name="comment"
        id="comment"
        v-model="comment"
        cols="35"
        rows="2"
      ></textarea>
      <button class="small" @click="registComment">등록</button>
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters, mapState } from "vuex";
const commentStore = "commentStore";
export default {
  props: {
    modifyComment: Object,
  },
  computed: {
    ...mapGetters(commentStore, ["comments"]),
    ...mapState(commentStore, ["comments"]),
  },
  created: {},
  data() {
    return {
      // 차후 작성자 이름은 로그인 구현후 로그인한 사용자로 바꾼다.
      user_name: "오싸피",
      comment: "",
    };
  },
  methods: {
    ...mapActions(commentStore, ["regComment", "getComments", "updComment"]),
    registComment() {
      this.regComment({
        user_name: this.user_name,
        comment: this.comment,
        articleNo: this.$route.params.articleno,
      });
      this.comment = "";
    },
    updateComment() {
      this.updComment(this.modifyComment);
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
