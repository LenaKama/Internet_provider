package by.kamotskaya.epam.receiver;

import by.kamotskaya.epam.command.CommandResult;
import by.kamotskaya.epam.controller.RequestContent;
import by.kamotskaya.epam.entity.Tariff;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Lena Kamotskaya
 */
public class TariffReceiver {


    private static final Logger LOGGER = LogManager.getLogger(TariffReceiver.class);

    public TariffReceiver() {}

    public static CommandResult addNewTariff(RequestContent content) {

        String tName = content.getRequestParameters().get("t_name")[0];
        String connectionPayment = content.getRequestParameters().get("connection_payment")[0];
        String dailyFee = content.getRequestParameters().get("daily_fee")[0];
        String trafficLimit = content.getRequestParameters().get("traffic_limit")[0];
        String speedIn = content.getRequestParameters().get("speed_in")[0];
        String speedOut = content.getRequestParameters().get("speed_out")[0];
        String overrunFee = content.getRequestParameters().get("overrun_fee")[0];

        Tariff tariff = new Tariff();
        tariff.settName(tName);
        tariff.setConnectionPayment(Double.parseDouble(connectionPayment));
        tariff.setDailyFee(Double.parseDouble(dailyFee));
        tariff.setTrafficLimit(Integer.parseInt(trafficLimit));
        tariff.setSpeedIn(speedIn);
        tariff.setSpeedOut(speedOut);
        tariff.setOverrunFee(Double.parseDouble(overrunFee));
        return null;
    }
}
