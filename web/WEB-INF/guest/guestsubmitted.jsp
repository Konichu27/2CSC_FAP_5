<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <meta name="description" content="Application" />
  <title>Application</title>
  <style><%@include file="/css/guest.css"%></style>
</head>

<body>
  <jsp:include page="/WEB-INF/template/header-guest.html"/>
</div>
<div class="wrapper">
  <div id="content"></div>
  <div class="entry-title">
    <h1 class="entry-title">Application Page</h1>
  </div>
  <div class="columns">
    <div class="current">
      <h2>Current Status</h2>
      <div class="decision submitted">
        <p>Submitted</p>
      </div>
      <p>You may also check your email for any further updates on your application</p>
      <p>[extra info]</p>
      <p>If you have any issues, <a href="https://activelearning.ph/contact/">please contact us</a></p>
    </div>

    <div class="forms">
      <h2>Application Details</h2>
      <form class="form">
        <input disabled="disabled" name="details" id="salutations" placeholder="<%= request.getAttribute("salutations") %>" required autocomplete="off">
        <input disabled="disabled" name="details" id="first_name" placeholder="<%= request.getAttribute("first_name") %>" required autocomplete="off">
        <input disabled="disabled" name="details" id="last_name" placeholder="<%= request.getAttribute("last_name") %>" required autocomplete="off">
        <input disabled="disabled" name="details" id="mobile_number" type="<%= request.getAttribute("mobile_number") %>" placeholder="Mobile Number" required autocomplete="off">
        <input disabled="disabled" name="details" id="email" placeholder="<%= request.getAttribute("email") %>" required autocomplete="off">
        <input disabled="disabled" name="details" id="app_role" placeholder="<%= request.getAttribute("app_role") %>" required autocomplete="off">
        <div class="upload">
        <p class="upload">Upload Resume</p>
        </div>
      </form>
    </div>
  </div>
</div>
<jsp:include page="/WEB-INF/template/footer.html"/>
</body>

</html>