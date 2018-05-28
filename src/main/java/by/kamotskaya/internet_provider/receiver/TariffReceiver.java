package by.kamotskaya.internet_provider.receiver;

import by.kamotskaya.internet_provider.command.CommandResult;
import by.kamotskaya.internet_provider.constant.PagePath;
import by.kamotskaya.internet_provider.constant.ParamName;
import by.kamotskaya.internet_provider.controller.RequestContent;
import by.kamotskaya.internet_provider.dao.impl.TariffDAO;
import by.kamotskaya.internet_provider.entity.Tariff;
import by.kamotskaya.internet_provider.exception.ConnectionPoolException;
import by.kamotskaya.internet_provider.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;

/**
 * @author Lena Kamotskaya
 */
public class TariffReceiver {

    private static final Logger LOGGER = LogManager.getLogger(TariffReceiver.class);

    public static CommandResult deleteTariff(RequestContent content) {

        int tId = Integer.parseInt(content.getRequestParameters().get("tId")[0]);
        try {
            TariffDAO tariffDAO = new TariffDAO();
            tariffDAO.delete(tId);
        } catch (DAOException | ConnectionPoolException e) {
            content.putRequestAttribute(ParamName.ERROR_MESSAGE, "Error is occurred while deleting a tariff.");
            return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return GoToPageReceiver.goToTariffs(content);
    }

    public static CommandResult addNewTariff(RequestContent content) {

        String tName = content.getRequestParameters().get("t_name")[0];
        String connectionPayment = content.getRequestParameters().get("connection_payment")[0];
        String dailyFee = content.getRequestParameters().get("daily_fee")[0];
        String trafficLimit = content.getRequestParameters().get("traffic_limit")[0];
        String speedIn = content.getRequestParameters().get("speed_in")[0];
        String speedOut = content.getRequestParameters().get("speed_out")[0];
        String overrunFee = content.getRequestParameters().get("overrun_fee")[0];

        Tariff tariff = new Tariff();
        tariff.setTName(tName);
        tariff.setConnectionPayment(Double.parseDouble(connectionPayment));
        tariff.setDailyFee(Double.parseDouble(dailyFee));
        tariff.setTrafficLimit(Integer.parseInt(trafficLimit));
        tariff.setSpeedIn(speedIn);
        tariff.setSpeedOut(speedOut);
        tariff.setOverrunFee(Double.parseDouble(overrunFee));
        try {
            TariffDAO tariffDAO = new TariffDAO();
            tariffDAO.add(tariff);
        } catch (DAOException | ConnectionPoolException e) {
            content.putRequestAttribute("errorMessage", "Error is occurred while adding a new tariff.");
            return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.TARIFFS);
    }

    public static CommandResult addSale(RequestContent content) {
        int tId = Integer.parseInt(content.getRequestParameters().get("tId")[0]);

        try {
            TariffDAO tariffDAO = new TariffDAO();
            Tariff tariff = tariffDAO.findTariffById(tId);
            tariff.setSalePercent(Integer.parseInt(content.getRequestParameters().get("salePercent")[0]));
            tariff.setSaleExpirationDate(Date.valueOf(content.getRequestParameters().get("saleExpirationDate")[0]));
            tariffDAO.update(tariff);
        } catch (DAOException | ConnectionPoolException e) {
            content.putRequestAttribute("errorMessage", "Error is occurred while finding a tariff.");
            return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.TARIFFS);
    }
}

