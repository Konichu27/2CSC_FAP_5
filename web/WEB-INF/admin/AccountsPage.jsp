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
    <meta name="description" content="Accounts Page" />
    <title>Accounts Page</title>
    <style><%@include file="/WEB-INF/css/admin.css"%></style>
    <link rel="shortcut icon"
        href="https://activelearning.ph/wp-content/uploads/2021/03/cropped-favicon-1-192x192.png" />
</head>

<body>
    <main>
        <jsp:include page="/WEB-INF/header-admin.jsp"/>
        <div class="container-whole">
           

            <div class="main-body">
                <div >
                    <h2 class="title">ACTIVE LEARNING APPLICANTS</h2>
                </div>
                <div class="select">
                    <label for="jobDropdown">Role:</label>
                    <select id="jobDropdown" oninput="filterTable()">
                        <option>All</option>
                        <option>Admin</option>
                        <option>Guest</option>
                        
                    </select>
                </div>
                <div class="table">
                    <table id="myTable">
                        <thead>
                            <tr>
                                <th>Username</th>
                                <th>Role</th>
                                
                            </tr>
                        </thead>
                        <tbody>
                            <%= request.getAttribute("table") %>
                            <!-- More rows here -->
                        </tbody>
                    </table>
                </div>

                <div class="reports">
                    <h3>Generate Report:</h3>
                    <button type="printSelf" class="btn" value="self_rec">Self-Record</button>
                    <button type="printFull" class="btn" value="full_rec">Full-Record</button>

                </div>
            </div>
        </div>
       
    </main>
    <script>
        function filterTable() {
            let dropdown, table, rows, cells, job, filter;
            dropdown = document.getElementById("jobDropdown");
            table = document.getElementById("myTable");
            rows = table.getElementsByTagName("tr");
            filter = dropdown.value;

            for (let row of rows) {
                cells = row.getElementsByTagName("td");
                job = cells[1] || null;
                if (filter === "All" || !job || (filter === job.textContent)) {
                    row.style.display = "";
                }
                else {
                    row.style.display = "none";
                }
            }
        }
    </script>
</body>

</html>