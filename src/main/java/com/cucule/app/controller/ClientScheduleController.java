package com.cucule.app.controller;

import com.cucule.app.request.CancelClientScheduleForm;
import com.cucule.app.request.ChangeClientScheduleForm;
import com.cucule.app.request.EditClientScheduleForm;
import com.cucule.app.request.RegisterClientScheduleForm;
import com.cucule.common.annotation.ClientAuth;
import com.cucule.common.global.CuculeConstants;
import com.cucule.common.logging.CuculeSessionManager;
import com.cucule.common.util.CuculeStringUtils;
import com.cucule.service.ClientScheduleService;
import com.cucule.service.input.ChangeClientScheduleInput;
import com.cucule.service.input.EditClientScheduleInput;
import com.cucule.service.input.RegisterClientScheduleInput;
import com.cucule.service.output.ClientScheduleModel;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import static com.cucule.common.logging.CuculeSessionManager.fetchClientId;

@ClientAuth
@Controller
public class ClientScheduleController extends ClientBaseController {

    @Autowired
    public ClientScheduleService clientScheduleService;

    @RequestMapping(path = "/client/schedule", method = RequestMethod.GET)
    public String initClientSchedule(HttpServletRequest request, @RequestParam String date, Model model) throws Exception {

        fetchClientSchedule(request, date, model);
        return "client_schedule";
    }

    @RequestMapping(path = "/client/schedule/register", method = RequestMethod.POST)
    public String registerClientSchedule(@ModelAttribute @Valid RegisterClientScheduleForm form, BindingResult bindingResult, @RequestParam String date, HttpServletRequest request, Model model) throws Exception {

        if (hasValidation(bindingResult, model)) {
            form.setDisplayName("register");
            model.addAttribute("form", form);
            fetchClientSchedule(request, date, model);
            return "client_schedule";
        }

        RegisterClientScheduleInput input = createRegisterInput(form, fetchClientId(request));
        boolean wasApplied = clientScheduleService.registerClientSchedule(input);

        String redirectName;
        if (wasApplied) {
            // 登録日付けに移動する
            redirectName = "redirect:/client/schedule?date=" + fetchForwardDate(form.getRaitenDate());
        } else {
            redirectName = "redirect:/error_page";
        }
        return redirectName;
    }

    @RequestMapping(path = "/client/schedule/edit", method = RequestMethod.POST)
    public String editClientSchedule(@ModelAttribute @Valid EditClientScheduleForm form, BindingResult bindingResult, @RequestParam String date, HttpServletRequest request, Model model) throws Exception {

        if (hasValidation(bindingResult, model)) {
            form.setDisplayName("edit");
            model.addAttribute("form", form);
            fetchClientSchedule(request, date, model);
            return "client_schedule";
        }

        EditClientScheduleInput input = createEditInput(form);
        boolean wasApplied = clientScheduleService.editClientSchedule(input);
        String redirectName;
        if (wasApplied) {
            // 編集後の日付けに移動する
            redirectName = "redirect:/client/schedule?date=" + fetchForwardDate(form.getRaitenDate());
        } else {
            redirectName = "redirect:/error_page";
        }
        return redirectName;
    }

    @RequestMapping(path = "/client/schedule/change", method = RequestMethod.POST)
    public String changeClientSchedule(@ModelAttribute ChangeClientScheduleForm form, @RequestParam String date, HttpServletRequest request) throws Exception {

        ChangeClientScheduleInput input = createChangeInput(form, request);
        boolean wasApplied = clientScheduleService.changeClientSchedule(input);
        String redirectName;
        // TODO 変える
        if (wasApplied) {
            // 編集後の日付けに移動する
            redirectName = "redirect:/client/schedule?date=" + date;
            ;
        } else {
            redirectName = "redirect:/error_page";
        }
        return redirectName;
    }

    @RequestMapping(path = "/client/schedule/cancel", method = RequestMethod.POST)
    public String cancelClientSchedule(@ModelAttribute CancelClientScheduleForm form, @RequestParam String date, HttpServletRequest request, Model model) throws Exception {
        boolean wasApplied = clientScheduleService.cancelClientSchedule(form.getReservationId());
        String redirectName;
        if (wasApplied) {
            // 元々いた日付けに移動する
            redirectName = "redirect:/client/schedule?date=" + date;
        } else {
            redirectName = "redirect:/error_page";
        }
        return redirectName;
    }

    private RegisterClientScheduleInput createRegisterInput(RegisterClientScheduleForm form, String clientId) throws ParseException {
        String raitenDate = form.getRaitenDate();

        raitenDate = StringUtils.substring(raitenDate, 0, 10);
        raitenDate = StringUtils.replace(raitenDate, "年", "-");
        raitenDate = StringUtils.replace(raitenDate, "月", "-");

        StringBuffer s = new StringBuffer();
        s.append(raitenDate);
        s.append(CuculeConstants.SPACE);
        s.append(form.getStartTime());

        StringBuffer e = new StringBuffer();
        e.append(raitenDate);
        e.append(CuculeConstants.SPACE);
        e.append(form.getEndTime());

        DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm").withLocale(Locale.JAPAN);
        Date startTime = dtf.parseDateTime(s.toString()).toDate();
        Date endTime = dtf.parseDateTime(e.toString()).toDate();

        RegisterClientScheduleInput input = new RegisterClientScheduleInput();
        input.setClientId(clientId);
        input.setStaffId(form.getStaffId());
        input.setStartTime(startTime);
        input.setEndTime(endTime);
        input.setNeedTime(Integer.parseInt(form.getNeedTime()));
        input.setLastNameKana(CuculeStringUtils.replaceHiraAndHarfKanaToFullWidthKana(CuculeStringUtils.allTrim(form.getLastNameKana())));
        input.setFirstNameKana(CuculeStringUtils.replaceHiraAndHarfKanaToFullWidthKana(CuculeStringUtils.allTrim(form.getFirstNameKana())));
        input.setLastNameKanji(CuculeStringUtils.allTrim(form.getLastNameKanji()));
        input.setFirstNameKanji(CuculeStringUtils.allTrim(form.getFirstNameKanji()));

        return input;
    }

    private EditClientScheduleInput createEditInput(EditClientScheduleForm form) {
        String raitenDate = form.getRaitenDate();

        raitenDate = StringUtils.substring(raitenDate, 0, 10);
        raitenDate = StringUtils.replace(raitenDate, "年", "-");
        raitenDate = StringUtils.replace(raitenDate, "月", "-");

        StringBuffer s = new StringBuffer();
        s.append(raitenDate);
        s.append(CuculeConstants.SPACE);
        s.append(form.getStartTime());

        StringBuffer e = new StringBuffer();
        e.append(raitenDate);
        e.append(CuculeConstants.SPACE);
        e.append(form.getEndTime());

        DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm").withLocale(Locale.JAPAN);
        Date startTime = dtf.parseDateTime(s.toString()).toDate();
        Date endTime = dtf.parseDateTime(e.toString()).toDate();

        EditClientScheduleInput input = new EditClientScheduleInput();
        input.setReservationId(form.getReservationId());
        input.setStaffId(form.getStaffId());
        input.setStartTime(startTime);
        input.setEndTime(endTime);
        input.setNeedTime(Integer.parseInt(form.getNeedTime()));
        return input;
    }

    private ChangeClientScheduleInput createChangeInput(ChangeClientScheduleForm form, HttpServletRequest request) {
        ChangeClientScheduleInput input = new ChangeClientScheduleInput();
        input.setClientId(CuculeSessionManager.fetchClientId(request));
        input.setReservationId(form.getReservationId());
        input.setPriority(Integer.parseInt(form.getPriority()));
        input.setStartTime(Float.valueOf(form.getStartTime()) / 60 / 60);
        input.setEndTime(Float.valueOf(form.getEndTime()) / 60 / 60);
        input.setNeedTime(Integer.parseInt(form.getNeedTime()) / 60 / 60);
        return input;
    }

    private void fetchClientSchedule(HttpServletRequest request, @RequestParam String date, Model model) throws ParseException {
        ClientScheduleModel clientScheduleModel = this.clientScheduleService.initClientSchedule(fetchClientId(request), date);

        DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/dd(E)").withLocale(Locale.JAPAN);
        Integer year = Integer.parseInt(StringUtils.substring(date, 0, 4));
        Integer month = Integer.parseInt(StringUtils.substring(date, 4, 6));
        Integer day = Integer.parseInt(StringUtils.substring(date, 6, 8));
        DateTime dt = new DateTime(year, month, day, 0, 0);
        clientScheduleModel.setCurrentDate(dtf.print(dt));
        clientScheduleModel.setCurrentDateParam(date);

        model.addAttribute("clientScheduleModel", clientScheduleModel);
    }

    private String fetchForwardDate(String forwardDate) {
        //e.g "2016年11月12日(土)"
        String redirectName;
        forwardDate = StringUtils.substring(forwardDate, 0, 10);
        forwardDate = StringUtils.replace(forwardDate, "年", "");
        forwardDate = StringUtils.replace(forwardDate, "月", "");
        return forwardDate;
    }
}
