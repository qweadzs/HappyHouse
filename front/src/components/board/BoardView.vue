<template>
  <v-container class="bv-example-row mt-3">
    <v-row>
      <v-col>
        <v-alert show><h3>글보기</h3></v-alert>
      </v-col>
    </v-row>
    <v-row class="mb-1">
      <v-col class="text-left">
        <v-btn
          :disabled="loading"
          class="ma-1"
          color="grey"
          outlined
          @click="listArticle"
          >목록</v-btn
        >
      </v-col>
      <v-col class="text-right">
        <v-btn
          :disabled="loading"
          class="ma-1"
          color="grey"
          outlined
          v-if="article.userid === userInfo.username"
          @click="moveModifyArticle"
          >글수정</v-btn
        >
        <v-btn
          :disabled="loading"
          class="ma-1"
          color="grey"
          outlined
          v-if="article.userid === userInfo.username"
          @click="removeArticle"
          >글삭제</v-btn
        >
      </v-col>
    </v-row>
    <v-row class="mb-1">
      <v-col>
        <b-card
          :header-html="`<h3>${article.articleno}.
          ${article.subject} [${article.hit}]</h3><div><h6>${article.userid}</div><div>${article.regtime}</h6></div>`"
          class="mb-2"
          border-variant="dark"
          no-body
        >
          <b-card-body class="text-left">
            <div v-html="message"></div>
          </b-card-body>
        </b-card>
      </v-col>
    </v-row>

    <comment-write :articleno="articleno" />
    <comment-write
      v-if="isModifyShow && modifyComment != null"
      :modifyComment="modifyComment"
      @modify-comment-cancel="onModifyCommentCancel"
    />
    <comment
      v-for="(comment, index) in comments"
      :key="index"
      :comment="comment"
      @modify-comment="onModifyComment"
    />
  </v-container>
</template>

<script>
// import moment from "moment";
import { getArticle, deleteArticle } from "@/api/board";
// import { commentList } from "@/api/comment";
import { mapActions, mapGetters, mapState } from "vuex";
import Comment from "@/components/board/child/comment/Comment";
import CommentWrite from "@/components/board/child/comment/CommentWrite";
const memberStore = "memberStore";
const commentStore = "commentStore";

export default {
  data() {
    return {
      article: {},
      articleno: "",
      isModifyShow: false,
      modifyComment: Object,
    };
  },
  components: {
    Comment,
    CommentWrite,
  },
  computed: {
    // ...mapState(commentStore, ["comments"]),
    ...mapGetters(commentStore, ["comments"]),
    ...mapState(memberStore, ["userInfo"]),
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
    // changeDateFormat() {
    //   return moment(new Date(this.article.regtime)).format(
    //     "YYYY.MM.DD hh:mm:ss"
    //   );
    // },
  },
  created() {
    getArticle(
      this.$route.params.articleno,
      (response) => {
        console.log("BoardView ano = " + this.$route.params.articleno);
        this.article = response.data;
      },
      (error) => {
        console.log("삭제시 에러발생!!", error);
      }
    );
    this.getComments(this.$route.params.articleno);
  },
  methods: {
    ...mapActions(commentStore, ["getComments"]),
    // ...mapMutations(commentStore, ["SET_COMMENTS"]),
    // ...mapActions(commentStore, ["getComments"]),
    listArticle() {
      this.$router.push({ name: "BoardList" });
    },
    moveModifyArticle() {
      this.$router.replace({
        name: "BoardUpdate",
        params: { articleno: this.article.articleno },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    removeArticle() {
      if (confirm("정말로 삭제?")) {
        deleteArticle(this.article.articleno, () => {
          this.$router.push({ name: "BoardList" });
        });
      }
    },
    onModifyComment(comment) {
      this.isModifyShow = true;
      this.modifyComment = comment;
    },
    onModifyCommentCancel(isShow) {
      this.isModifyShow = isShow;
    },
  },
};
</script>

<style></style>
