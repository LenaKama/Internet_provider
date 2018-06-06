package by.kamotskaya.internet_provider.receiver;

import by.kamotskaya.internet_provider.command.CommandResult;
import by.kamotskaya.internet_provider.constant.PagePath;
import by.kamotskaya.internet_provider.constant.ParamName;
import by.kamotskaya.internet_provider.controller.RequestContent;
import by.kamotskaya.internet_provider.dao.TariffDAO;
import by.kamotskaya.internet_provider.entity.Tariff;
import by.kamotskaya.internet_provider.exception.ConnectionPoolException;
import by.kamotskaya.internet_provider.exception.DAOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;

/**
 * Class for executing commands which relates to tariffs.
 *
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

        String tName = content.getRequestParameters().get(ParamName.T_NAME)[0];
        String connectionPayment = content.getRequestParameters().get(ParamName.CONNECTION_PAYMENT)[0];
        String dailyFee = content.getRequestParameters().get(ParamName.DAILY_FEE)[0];
        String trafficLimit = content.getRequestParameters().get(ParamName.TRAFFIC_LIMIT)[0];
        String speedIn = content.getRequestParameters().get(ParamName.SPEED_IN)[0];
        String speedOut = content.getRequestParameters().get(ParamName.SPEED_OUT)[0];
        String overrunFee = content.getRequestParameters().get(ParamName.OVERRUN_FEE)[0];

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
            content.putRequestAttribute(ParamName.ERROR_MESSAGE, "Error is occurred while adding a new tariff.");
            return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return GoToPageReceiver.goToTariffs(content);
    }

    public static CommandResult addSale(RequestContent content) {
        int tId = Integer.parseInt(content.getRequestParameters().get(ParamName.T_ID)[0]);

        try {
            TariffDAO tariffDAO = new TariffDAO();
            Tariff tariff = tariffDAO.findTariffById(tId);
            tariff.setSalePercent(Integer.parseInt(content.getRequestParameters().get(ParamName.SALE_PERCENT)[0]));
            tariff.setSaleExpirationDate(Date.valueOf(content.getRequestParameters().get(ParamName.SALE_EXPIRATION_DATE)[0]));
            tariff.setConnectionPayment(tariff.getConnectionPayment() - tariff.getSalePercent() * tariff.getConnectionPayment() / 100);
            tariffDAO.update(tariff);
        } catch (DAOException | ConnectionPoolException e) {
            content.putRequestAttribute(ParamName.ERROR_MESSAGE, "Error is occurred while adding a sale.");
            return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return GoToPageReceiver.goToTariffs(content);
    }

    public static CommandResult updateTariff(RequestContent content) {
        int tId = Integer.parseInt(content.getRequestParameters().get(ParamName.T_ID)[0]);
        try {
            TariffDAO tariffDAO = new TariffDAO();
            Tariff tariff = tariffDAO.findTariffById(tId);
            tariff.setTName(content.getRequestParameters().get(ParamName.T_NAME)[0]);
            tariff.setConnectionPayment(Double.parseDouble(content.getRequestParameters().get(ParamName.CONNECTION_PAYMENT)[0]));
            tariff.setDailyFee(Double.parseDouble(content.getRequestParameters().get(ParamName.DAILY_FEE)[0]));
            if (content.getRequestParameters().get(ParamName.TRAFFIC_LIMIT)[0] != null) {
                tariff.setTrafficLimit(Integer.parseInt(content.getRequestParameters().get(ParamName.TRAFFIC_LIMIT)[0]));
            } else {
                tariff.setTrafficLimit(0);
            }
            tariff.setSpeedIn(content.getRequestParameters().get(ParamName.SPEED_IN)[0]);
            tariff.setSpeedOut(content.getRequestParameters().get(ParamName.SPEED_OUT)[0]);
            tariff.setOverrunFee(Double.parseDouble(content.getRequestParameters().get(ParamName.OVERRUN_FEE)[0]));
            tariffDAO.update(tariff);
        } catch (ConnectionPoolException | DAOException e) {
            content.putRequestAttribute(ParamName.ERROR_MESSAGE, "Error while updating a tariff.");
            return new CommandResult(CommandResult.ResponseType.FORWARD, PagePath.ERROR);
        }
        return GoToPageReceiver.goToTariffs(content);
    }
}

