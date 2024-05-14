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
      <div class="decision not-submitted">
        <p>Not Submitted</p>
      </div>
      <p>You may also check your email for any further updates on your application</p>
      <p>[extra info]</p>
      <p>If you have any issues, <a href="https://activelearning.ph/contact/">please contact us</a></p>
    </div>

    <div class="forms">
      <h2>Application Details</h2>
      <form class="form" action="/2CSC_FAP_5/uploads" method="POST" enctype="multipart/form-data">
        <select id="details" name="salutations" placeholder="Salutations" required autocomplete="off" enctype="multipart/form-data">
            <option value="">Salutations</option>
            <option value="Mr.">Mr.</option>
            <option value="Ms.">Ms.</option>
            <option value="Mrs.">Mrs.</option>
            <option value="Dr.">Dr.</option>
            <option value="Engr.">Engr.</option>
        </select>
        <input id="details" name="first_name" placeholder="First Name" required autocomplete="off">
        <input id="details" name="last_name" placeholder="Last Name" required autocomplete="off">
        <input id="details" name="mobile_number" type="tel" pattern="[0-9]{11}" placeholder="Mobile #" required autocomplete="off">
        <input id="details" name="email" disabled="disabled" placeholder="${sessionScope.uname}" value="${sessionScope.uname}" required autocomplete="off">
        <select id="details" name="app_role" placeholder="Role" required autocomplete="off">
            <option value="">Applied Role</option>
            <option value="Instructors (Part-time/Full-time)">Instructors (Part-time/Full-time)</option>
            <option value="HR and Admin Manager (Full-time)">HR and Admin Manager (Full-time)</option>
            <option value="HR and Admin Officer (Full-time)">HR and Admin Officer (Full-time)</option>
            <option value="Sales Officers (Full-time)">Sales Officers (Full-time)</option>
            <option value="Graphic Designer (Part-time)">Graphic Designer (Part-time)</option>
            <option value="Admin Officer (Full-time)">Admin Officer (Full-time)</option>
            <option value="Sales and Marketing Supervisor (Full-Time)">Sales and Marketing Supervisor (Full-Time)</option>
            <option value="Sales and Marketing Manager (Full-Time)">Sales and Marketing Manager (Full-Time)</option>
            <option value="Graphic Designer (Part-Time)">Graphic Designer (Part-Time)</option>
            <option value="Marketing and Communication Officer (Full-Time)">Marketing and Communication Officer (Full-Time)</option>
            <option value="IT Support (Full-Time)">IT Support (Full-Time)</option>
        </select>
        <div class="upload">
        <input type="file" name="file" required>
        </div>
        <label class="checkbox">
        <input type="checkbox"> <p class="checkbox">I have read and agree to ActiveLearning's
        <a href="https://activelearning.ph/data-privacy-policy/?fbclid=IwAR0yKcTQSWinkr1M9c1ZCSWp8UbU5OZrHw63Urx1eP4YnrJ8_rHDosWDyeE">data privacy agreement</a>
        </p>
        </label>
       
        <br>
        <button type="submit" class="btn">SUBMIT</button>
      </form>
    </div>
  </div>
</div>
<jsp:include page="/WEB-INF/template/footer.html"/>
</body>

</html>