import { apiInstance } from "./index.js";

const api = apiInstance();

function mailWrite(mail, success, fail) {
  api.post(`/mail/write`, JSON.stringify(mail)).then(success).catch(fail);
}

// function logout(success, fail)

export { mailWrite };
