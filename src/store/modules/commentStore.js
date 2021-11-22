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
          console.log("getComments 실행");
          commit("SET_COMMENTS", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    regComment: ({ commit, dispatch }, comment) => {
      writeComment(comment, ({ data }) => {
        console.log(commit);
        let msg = "등록 처리시 문제가 발생했습니다.";
        if (data === "success") {
          msg = "등록이 완료되었습니다.";
        }
        alert(msg);
        dispatch("getComments", comment.articleNo);
      });
    },
    updComment: ({ commit, dispatch }, comment) => {
      modifyComment(comment, ({ data }) => {
        let msg = "수정 처리시 문제가 발생했습니다.";
        if (data === "success") {
          msg = "수정이 완료되었습니다.";
        }
        console.log(commit);
        alert(msg);
        dispatch("getComments", comment.articleNo);
        // dispatch("getComments", comment.articleNo);
      });
    },
    delComment: ({ commit, dispatch }, comment) => {
      deleteComment(comment.comment_no, ({ data }) => {
        let msg = "삭제 처리시 문제가 발생했습니다.";
        if (data === "success") {
          msg = "삭제가 완료되었습니다.";
        }
        console.log(commit);
        alert(msg);
        dispatch("getComments", comment.articleNo);
      });
    },
  },
};

export default commentStore;
