<template>
  <div v-show="isShow" class="comment">
    <div class="head">
      {{ comment.user_name }} ({{ getFormatDate(comment.comment_time) }})
    </div>
    <div class="content" v-html="enterToBr(comment.comment)"></div>
    <!-- 로그인 기능 구현 후 로그인한 자신의 글에만 보이게 한다. -->
    <div class="cbtn">
      <label
        v-if="comment.user_name == userInfo.username"
        @click="modifyCommentView"
        >수정</label
      >|<label
        v-if="comment.user_name == userInfo.username"
        @click="deleteComment"
        >삭제</label
      >
    </div>
  </div>
</template>

<script>
import moment from "moment";
import { mapActions, mapState } from "vuex";
const commentStore = "commentStore";
const memberStore = "memberStore";
export default {
  props: {
    comment: Object,
  },
  data() {
    return {
      isShow: true,
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(commentStore, ["delComment"]),
    modifyCommentView() {
      this.$emit("modify-comment", {
        comment_no: this.comment.comment_no,
        comment: this.comment.comment,
        articleno: this.comment.articleno,
      });
    },
    deleteComment() {
      this.delComment(this.comment);
      // this.$router.go();
    },
    getFormatDate(regtime) {
      return moment(new Date(regtime)).format("YYYY-MM-DD HH:mm:ss");
    },
    enterToBr(str) {
      // 문자열에 enter값을 <br />로 변경.(html상에서 줄바꿈 처리)
      return String(str).replace(/(?:\r\n|\r|\n)/g, "<br />");
    },
  },
};
</script>

<style scoped>
.comment {
  text-align: left;
  border-radius: 5px;
  background-color: #d6e7fa;
  padding: 10px 20px;
  margin: 10px;
}
.head {
  font-weight: bold;
  margin-bottom: 5px;
}
.content {
  padding: 5px;
}
.cbtn {
  text-align: right;
  color: steelblue;
  margin: 5px 0px;
}
</style>
