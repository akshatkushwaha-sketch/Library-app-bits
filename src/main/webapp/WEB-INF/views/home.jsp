<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Library Management System</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; }
        body { font-family: 'Segoe UI', Arial, sans-serif; background: #f0f2f5; color: #333; }
        header {
            background: #2c3e50; color: white;
            padding: 18px 40px; display: flex; align-items: center; justify-content: space-between;
        }
        header h1 { font-size: 1.6rem; }
        nav a { color: #ecf0f1; text-decoration: none; margin-left: 20px; font-size: 0.95rem; }
        nav a:hover { text-decoration: underline; }
        .hero {
            text-align: center; padding: 60px 20px;
        }
        .hero h2 { font-size: 2rem; margin-bottom: 12px; color: #2c3e50; }
        .hero p { color: #666; margin-bottom: 32px; font-size: 1.05rem; }
        .cards { display: flex; justify-content: center; gap: 30px; flex-wrap: wrap; }
        .card {
            background: white; border-radius: 10px; padding: 36px 40px;
            box-shadow: 0 2px 12px rgba(0,0,0,0.1); min-width: 220px; text-align: center;
            transition: transform 0.2s;
        }
        .card:hover { transform: translateY(-4px); }
        .card h3 { font-size: 1.2rem; margin-bottom: 14px; color: #2c3e50; }
        .card a {
            display: inline-block; background: #3498db; color: white;
            padding: 9px 22px; border-radius: 5px; text-decoration: none; font-size: 0.9rem;
        }
        .card a:hover { background: #2980b9; }
        footer { text-align: center; padding: 20px; color: #999; font-size: 0.85rem; margin-top: 40px; }
    </style>
</head>
<body>
<header>
    <h1>Library Management System</h1>
    <nav>
        <a href="/authors">Authors</a>
        <a href="/books">Books</a>
    </nav>
</header>
<div class="hero">
    <h2>Welcome to the Library</h2>
    <p>Manage your collection of books and authors in one place.</p>
    <div class="cards">
        <div class="card">
            <h3>Authors</h3>
            <p style="color:#666;margin-bottom:16px;font-size:0.9rem;">Browse, add, and edit authors</p>
            <a href="/authors">Manage Authors</a>
        </div>
        <div class="card">
            <h3>Books</h3>
            <p style="color:#666;margin-bottom:16px;font-size:0.9rem;">Browse, add, and edit books</p>
            <a href="/books">Manage Books</a>
        </div>
    </div>
</div>
<footer>Library Management System &copy; 2024</footer>
</body>
</html>
