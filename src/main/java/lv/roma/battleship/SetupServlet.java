package lv.roma.battleship;

import lv.roma.battleship.model.CellState;
import lv.roma.battleship.model.Field;
import lv.roma.battleship.model.Game;
import lv.roma.battleship.model.Player;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(urlPatterns = "/setup")
public class SetupServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] selected = request.getParameterValues("cells");

        var player = (Player) request.getSession().getAttribute("player");

        player.getOwnField().clear();

        if (selected != null) {
            System.out.println(Arrays.toString(selected));
            for (String addr : selected) {
                player.getOwnField().setState(addr, CellState.SHIP);
            }
        }

            if (player.getOwnField().isValid()) {
                response.sendRedirect("/setup");

            }else {
                request.setAttribute("message", "wrong option");
                request.getRequestDispatcher("/WEB-INF/setupShips.jsp").include(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var player = (Player) request.getSession().getAttribute("player");
        var game = (Game) request.getSession().getAttribute("game");

        if (player ==null) {
            response.sendRedirect("/index.html");
            return;
        }
        if (game.gameReady()){
            response.sendRedirect("/play");
        }

        if (player.isReadyToPlay()){

            request.getRequestDispatcher("/WEB-INF/waitSetup.jsp").include(request, response);
            return;
        }
        if (game.isReady()){
            request.getRequestDispatcher("/WEB-INF/setupShips.jsp").include(request, response);
        }

        else {
            response.sendRedirect("/battle");
        }
    }
}
