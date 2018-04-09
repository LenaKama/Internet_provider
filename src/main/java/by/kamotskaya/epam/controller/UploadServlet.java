package by.kamotskaya.epam.controller;

import by.kamotskaya.epam.constant.PagePath;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Lena Kamotskaya
 */
@WebServlet(urlPatterns = "/UploadServlet")
@MultipartConfig
public class UploadServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(UploadServlet.class);
    private static final String SAVE_DIR = "uploadFiles";
    private static final String FILE_NAME = "file.xml";


    public UploadServlet() {
        super();
    }

    @Override
    public void init() throws ServletException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String appPath = request.getServletContext().getRealPath("");
        String savePath = appPath + SAVE_DIR;
        String fileName = null;

        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        List<Part> fileParts = request.getParts().stream()
                .filter(part -> "file".equals(part.getName()))
                .collect(Collectors.toList());
        request.setAttribute("message", "File isn't uploaded.");
        request.getRequestDispatcher(PagePath.ERROR).forward(request, response);

        for (Part part : fileParts) {
            fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
            if (fileName.equals(FILE_NAME) && fileName != null) {
                part.write(savePath + File.separator + fileName);
            } else {
                request.setAttribute("message", "Wrong file.");
                request.getRequestDispatcher(PagePath.ERROR).forward(request, response);
            }
        }

        LOGGER.log(Level.INFO, "File is uploaded.");
    }

    @Override
    public void destroy() {

        LOGGER.log(Level.INFO, "UploadServlet is destroyed");
        super.destroy();
    }
}
