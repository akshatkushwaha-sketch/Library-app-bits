<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Author – Library</title>
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
        .container { max-width: 520px; margin: 40px auto; padding: 0 20px; }
        .card { background: white; padding: 32px; border-radius: 10px; box-shadow: 0 2px 12px rgba(0,0,0,0.09); }
        h2 { margin-bottom: 24px; color: #2c3e50; font-size: 1.3rem; }
        .form-group { margin-bottom: 18px; }
        label { display: block; margin-bottom: 6px; font-size: 0.9rem; font-weight: 500; }
        input[type="text"], input[type="email"] {
            width: 100%; padding: 9px 12px; border: 1px solid #ccc;
            border-radius: 5px; font-size: 0.9rem;
        }
        input:focus { outline: none; border-color: #3498db; }
        .btn-submit {
            background: #27ae60; color: white; border: none;
            padding: 10px 28px; border-radius: 5px; cursor: pointer; font-size: 0.95rem;
        }
        .btn-submit:hover { background: #219a52; }
        .btn-cancel { color: #666; text-decoration: none; margin-left: 14px; font-size: 0.9rem; }
        .alert { padding: 10px 14px; border-radius: 5px; margin-bottom: 18px; font-size: 0.88rem; }
        .alert-error { background: #f8d7da; color: #721c24; border: 1px solid #f5c6cb; }
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
    <div class="card">
        <h2>Add New Author</h2>

        <c:if test="${not empty error}">
            <div class="alert alert-error">${error}</div>
        </c:if>

        <form action="/authors/add" method="post">
            <div class="form-group">
                <label for="name">Full Name</label>
                <input type="text" id="name" name="name" placeholder="e.g. Jane Austen" required />
            </div>
            <div class="form-group">
                <label for="email">Email Address</label>
                <input type="email" id="email" name="email" placeholder="author@example.com" required />
            </div>
            <div class="form-group">
                <label for="nationality">Nationality</label>
                <input type="text" id="nationality" name="nationality" placeholder="e.g. British" />
            </div>
            <div style="margin-top:24px;">
                <button type="submit" class="btn-submit">Save Author</button>
                <a href="/authors" class="btn-cancel">Cancel</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>
