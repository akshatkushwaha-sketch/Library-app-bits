<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Book – Library</title>
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
        input[type="text"], input[type="number"], select {
            width: 100%; padding: 9px 12px; border: 1px solid #ccc;
            border-radius: 5px; font-size: 0.9rem; background: white;
        }
        input:focus, select:focus { outline: none; border-color: #3498db; }
        .btn-submit {
            background: #3498db; color: white; border: none;
            padding: 10px 28px; border-radius: 5px; cursor: pointer; font-size: 0.95rem;
        }
        .btn-submit:hover { background: #2980b9; }
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
        <h2>Edit Book</h2>

        <c:if test="${not empty error}">
            <div class="alert alert-error">${error}</div>
        </c:if>

        <form action="/books/edit/${book.id}" method="post">
            <div class="form-group">
                <label for="title">Book Title</label>
                <input type="text" id="title" name="title" value="${book.title}" required />
            </div>
            <div class="form-group">
                <label for="genre">Genre</label>
                <input type="text" id="genre" name="genre" value="${book.genre}" />
            </div>
            <div class="form-group">
                <label for="publishedYear">Published Year</label>
                <input type="number" id="publishedYear" name="publishedYear" value="${book.publishedYear}" min="1000" max="2100" required />
            </div>
            <div class="form-group">
                <label for="authorId">Author</label>
                <select id="authorId" name="authorId" required>
                    <c:forEach var="author" items="${authors}">
                        <option value="${author.id}"
                            <c:if test="${author.id == book.author.id}">selected</c:if>>
                            ${author.name}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div style="margin-top:24px;">
                <button type="submit" class="btn-submit">Update Book</button>
                <a href="/books" class="btn-cancel">Cancel</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>
