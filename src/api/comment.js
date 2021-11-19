import { apiInstance } from "./index.js";

const api = apiInstance();

// articleNo에 해당하는 댓글 목록을 반환한다.
function commentList(articleno, success, fail) {
  api.get(`/comment/${articleno}`).then(success).catch(fail);
}
// commentNo에 해당하는 댓글을 삭제한다.
function deleteComment(commentNo, success, fail) {
  api.delete(`/comment/${commentNo}`).then(success).catch(fail);
}

export { commentList, deleteComment };
