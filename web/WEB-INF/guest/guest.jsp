<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <meta name="description" content="Application" />
  <title>Application</title>
  <style><%@include file="/WEB-INF/css/guest.css"%></style>
</head>

<body>
  <header>
    <div class="container">
      <img id="logo" src="ActiveLearningLogo.png" alt="Active Learning Logo">
    </div>
  </header>
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
      <form class="form">
        <select id="details" name="salutations" placeholder="Salutations" required autocomplete="off">
            <option value="Mr.">Mr.</option>
            <option value="Ms.">Ms.</option>
            <option value="Mrs.">Mrs.</option>
            <option value="Dr.">Dr.</option>
            <option value="Engr.">Engr.</option>
        </select>
        <input id="details" name="first name" placeholder="First Name" required autocomplete="off">
        <input id="details" name="last name" placeholder="Last Name" required autocomplete="off">
        <input id="details" name="mobile" type="tel" pattern="[0-9]{11}" placeholder="Mobile #" required autocomplete="off">
        <input id="details" name="landline" type="tel" pattern="[0-9]{3}-[0-9]{4}" placeholder="Landline #" required autocomplete="off">
        <input id="details" name="role" placeholder="Role" required autocomplete="off">
        <div class="upload">
        <p class="upload">Upload Resume</p>
        </div>
        <label class="checkbox">
        <input type="checkbox"> <p class="checkbox">I have read and agree to ActiveLearning's
        <a href="https://activelearning.ph/data-privacy-policy/?fbclid=IwAR0yKcTQSWinkr1M9c1ZCSWp8UbU5OZrHw63Urx1eP4YnrJ8_rHDosWDyeE">data privacy agreement</a>
        </p>
        </input>
        </label>
       
        <br>
        <button type="submit" class="btn">SUBMIT</button>
      </form>
    </div>
  </div>
</div>

</body>

</html>