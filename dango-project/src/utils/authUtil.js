export function isAuthed() {
  return localStorage.getItem("loginUser") !== null;
}
