export default function GithubLoginButton() {
  return (
    <a href="http://localhost:8080/oauth2/authorization/github">
      <button style={{ padding: "10px", marginTop: "10px" }}>
        Login with GitHub
      </button>
    </a>
  );
}
