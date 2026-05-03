<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Authors – Library</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; }
        body { font-family: 'Segoe UI', Arial, sans-serif; background: #f0f2f5; color: #333; }
        header {
            background: #2c3e50; color: white;
            padding: 18px 40px; display: flex; align-items: center; justify-content: space-between;
        }
        header h1 { font-size: 1.4rem; }
        nav a { color: #ecf0f1; text-decoration: none; margin-left: 20px; font-size: 0.9rem; }
        nav a:hover { text-decoration: underline; }
        .container { max-width: 900px; margin: 30px auto; padding: 0 20px; }
        .top-bar { display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px; }
        .top-bar h2 { font-size: 1.4rem; color: #2c3e50; }
        .btn { background: #27ae60; color: white; padding: 9px 20px; border-radius: 5px; text-decoration: none; font-size: 0.9rem; }
        .btn:hover { background: #219a52; }
        .alert { padding: 12px 16px; border-radius: 5px; margin-bottom: 18px; font-size: 0.9rem; }
        .alert-success { background: #d4edda; color: #155724; border: 1px solid #c3e6cb; }
        .alert-error   { background: #f8d7da; color: #721c24; border: 1px solid #f5c6cb; }
        table { width: 100%; border-collapse: collapse; background: white; border-radius: 8px; overflow: hidden; box-shadow: 0 1px 8px rgba(0,0,0,0.08); }
        th { background: #2c3e50; color: white; padding: 12px 16px; text-align: left; font-size: 0.9rem; }
        td { padding: 11px 16px; border-bottom: 1px solid #eee; font-size: 0.9rem; }
        tr:last-child td { border-bottom: none; }
        tr:hover td { background: #f8f9fa; }
        .edit-link { color: #3498db; text-decoration: none; font-weight: 500; }
        .edit-link:hover { text-decoration: underline; }
    </style>
</head>
<body>
<header>
    <h1>Library Management System</h1>
    <nav>
        <a href="/">Home</a>
        <a href="/authors">Authors</a>
        <a href="/books">Books</a>
    </nav>
</header>
<div class="container">
    <div class="top-bar">
        <h2>All Authors</h2>
        <a href="/authors/add" class="btn">+ Add Author</a>
    </div>

    <c:if test="${not empty success}">
        <div class="alert alert-success">${success}</div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="alert alert-error">${error}</div>
    </c:if>

    <table>
        <thead>
        <tr>
            <th>#</th>
            <th>Name</th>
            <th>Email</th>
            <th>Nationality</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="author" items="${authors}" varStatus="st">
            <tr>
                <td>${st.count}</td>
                <td>${author.name}</td>
                <td>${author.email}</td>
                <td>${author.nationality}</td>
                <td><a href="/authors/edit/${author.id}" class="edit-link">Edit</a></td>
            </tr>
        </c:forEach>
        <c:if test="${empty authors}">
            <tr><td colspan="5" style="text-align:center;color:#999;padding:24px;">No authors found.</td></tr>
        </c:if>
        </tbody>
    </table>
</div>
</body>
</html>
