import {
  commentList,
  writeComment,
  modifyComment,
  deleteComment,
} from "@/api/comment.js";
const commentStore = {
  namespaced: true,
  state: {
    comments: [],
  },
  getters: {
    comments(state) {
      return state.comments;
    },
  },
  mutations: {
    SET_COMMENTS(state, payload) {
      state.comments = payload;
    },
  },
  actions: {
    getComments: ({ commit }, articleno) => {
      commentList(
        articleno,
        ({ data }) => {
          console.log(commit);
          commit("SET_COMMENTS", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    registComment: (comment, dispatch) => {
      console.log("뭐야");
      writeComment(comment, ({ data }) => {
        let msg = "등록 처리시 문제가 발생했습니다.";
        if (data === "success") {
          msg = "등록이 완료되었습니다.";
        }
        alert(msg);
        this.comment = "";
        dispatch("getComments(comment.articleno)");
      });
    },
    updateComment: (comment, dispatch) => {
      modifyComment(comment, ({ data }) => {
        let msg = "수정 처리시 문제가 발생했습니다.";
        if (data === "success") {
          msg = "수정이 완료되었습니다.";
        }
        alert(msg);
        dispatch("getComments(comment.articleno)");
        dispatch("updateCommentCancel");
      });
    },
    deleteComment: (commentNo, dispatch) => {
      deleteComment(commentNo, ({ data }) => {
        let msg = "삭제 처리시 문제가 발생했습니다.";
        if (data === "success") {
          msg = "삭제가 완료되었습니다.";
        }
        alert(msg);
        dispatch("getComments(comment.articleno)");
      });
    },
    updateCommentCancel() {
      // 부모 컴포넌트의 modify-comment-cancel에 false를 리턴.
      this.$emit("modify-comment-cancel", false);
    },
  },
};

export default commentStore;
