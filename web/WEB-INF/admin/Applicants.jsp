<!DOCTYPE html>
<html lang="en">
    
    <%
    boolean isAdmin = false;
    String def = "You must have be logged in as an admin to access this page.";
    try {
        isAdmin = session.getAttribute("urole").toString().equals("Admin");
        }
    catch (NullPointerException e) {
            isAdmin = false;
            def = "You must be logged in to access this page.";
        }
    if (!isAdmin) {
        request.getSession().setAttribute("error_message", def);
        response.sendRedirect("error.jsp");
        }
    %>
    
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta name="description" content="Applicant Page" />
    <title>Applicant Page</title>
    <style><%@include file="/css/admin.css"%></style>
    <link rel="shortcut icon"
        href="https://activelearning.ph/wp-content/uploads/2021/03/cropped-favicon-1-192x192.png" />
</head>

<body>
    <jsp:include page="/WEB-INF/template/header-admin.jsp"/>
    <div class="container-whole">
        <main>
            <div class="main-body">
                <div>
                    <h2 class="title">ACTIVE LEARNING APPLICANTS</h2>
                </div>
                <div class="select">
                    <label for="jobDropdown">Role:</label>
                    <select id="jobDropdown" oninput="filterTable()">
                        <option>All</option>
                        <option>HR and Admin Manager (Full-time)</option>
                        <option>HR and Admin Officer (Full-time)</option>
                        <option>Sales Officers (Full-time)</option>
                        <option>Graphic Designer (Part-time)</option>
                        <option>Admin Officer (Full-time)</option>
                        <option>Sales and Marketing Supervisor (Full-Time)</option>
                        <option>Sales and Marketing Manager (Full-Time)</option>
                        <option>Graphic Designer (Part-Time)</option>
                        <option>Marketing and Communication Officer (Full-Time)</option>
                        <option>IT Support (Full-Time)</option>
                    </select>
                </div>
                <form action="/2CSC_FAP_5/files" method="POST">
                    <div class="table">
                        <table id="myTable">
                            <thead>
                                <tr>
                                    <th>Archive?</th>
                                    <th>Salutations</th>
                                    <th>First Name</th>
                                    <th>Last Name</th>
                                    <th>Applied Role</th>
                                    <th>Email</th>
                                    <th>Mobile #</th>
                                    <th>Resume/CV</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%= request.getAttribute("table") %>
                                <!-- More rows here -->
                            </tbody>
                        </table>
                    </div>
                </form>
                <div class="reports">
                    <h3>Generate Report:</h3>
                    
                    <form action="/2CSC_FAP_5/downloadReport" method="POST">
                        <button type="submit" id="reportType" name="reportType" class="btn" value="current_app">Print Full Applicant Table</button>
                    </form>
                </div>
            </div>
        </main>
    </div>
    <script>
        function filterTable() {
            let dropdown, table, rows, cells, job, filter;
            dropdown = document.getElementById("jobDropdown");
            table = document.getElementById("myTable");
            rows = table.getElementsByTagName("tr");
            filter = dropdown.value;

            for (let row of rows) {
                cells = row.getElementsByTagName("td");
                job = cells[4] || null;
                if (filter === "All" || !job || (filter === job.textContent)) {
                    row.style.display = "";
                }
                else {
                    row.style.display = "none";
                }
            }
        }
    </script>
    <jsp:include page="/WEB-INF/template/footer.html"/>
</body>

</html>