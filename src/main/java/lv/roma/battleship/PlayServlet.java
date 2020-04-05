package lv.roma.battleship;


import lv.roma.battleship.model.Game;
import lv.roma.battleship.model.Player;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/play")
public class PlayServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var player = (Player) request.getSession().getAttribute("player");
        var game = (Game) request.getSession().getAttribute("game");

        if (player ==null) {
            response.sendRedirect("/index.html");
            return;

        } if (game.gameReady()) {

            request.getRequestDispatcher("/WEB-INF/playGame.jsp").include(request, response);
        } else
            response.sendRedirect("/setup");

    }
}
