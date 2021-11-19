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
import { mapActions, mapGetters } from "vuex";
const commentStore = "commentStore";
export default {
  props: {
    articleno: String,
    modifyComment: Object,
  },
  computed: {
    ...mapGetters(commentStore, ["comments"]),
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
    ...mapActions(commentStore, ["writeComment"]),
    registComment() {
      console.log(this.articleno);
      this.writeComment({
        user_name: this.user_name,
        comment: this.comment,
        articleno: this.articleno,
      });
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
