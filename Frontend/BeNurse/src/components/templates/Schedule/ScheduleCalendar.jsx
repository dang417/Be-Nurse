import React, { useState } from "react";
import { MdKeyboardArrowLeft, MdKeyboardArrowRight } from "react-icons/md";
import {
  CalendarWrapper,
  Header,
  Table,
  WeekdayRow,
  Weekday,
  StateWrapper,
  State,
  Td,
} from "./ScheduleCalendar.styles";

export default function ScheduleCalendar() {
  const today = new Date();
  const [currentDate, setCurrentDate] = useState(
    new Date(today.getFullYear(), today.getMonth(), 1),
  );

  const createCalendar = (date) => {
    const startDay = date.getDay();
    const totalDays = new Date(
      date.getFullYear(),
      date.getMonth() + 1,
      0,
    ).getDate();
    const prevMonthTotalDays = new Date(
      date.getFullYear(),
      date.getMonth(),
      0,
    ).getDate();

    let dates = [];
    for (let i = 1; i <= totalDays; i++) {
      dates.push({ day: i, isCurMonth: true });
    }

    for (let i = 0; i < startDay; i++) {
      dates.unshift({ day: prevMonthTotalDays - i, isCurMonth: false });
    }

    while (dates.length % 7 !== 0) {
      dates.push({ day: (dates.length % 7) + 1, isCurMonth: false });
    }

    let weeks = [];
    while (dates.length) {
      weeks.push(dates.splice(0, 7));
    }

    return weeks;
  };

  const prevMonth = () => {
    setCurrentDate(
      new Date(currentDate.getFullYear(), currentDate.getMonth() - 1, 1),
    );
  };

  const nextMonth = () => {
    setCurrentDate(
      new Date(currentDate.getFullYear(), currentDate.getMonth() + 1, 1),
    );
  };

  const weeks = createCalendar(currentDate);

  return (
    <CalendarWrapper>
      <Header>
        <button onClick={prevMonth}>
          <MdKeyboardArrowLeft />
        </button>
        <h2>
          {currentDate.getFullYear()}년 {currentDate.getMonth() + 1}월
        </h2>
        <button onClick={nextMonth}>
          <MdKeyboardArrowRight />
        </button>
      </Header>
      <StateWrapper>
        <State type={"day"}>DAY</State>
        <State type={"evening"}>EVENING</State>
        <State type={"night"}>NIGHT</State>
        <State type={"off"}>OFF</State>
      </StateWrapper>
      <Table>
        <WeekdayRow>
          <Weekday>일</Weekday>
          <Weekday>월</Weekday>
          <Weekday>화</Weekday>
          <Weekday>수</Weekday>
          <Weekday>목</Weekday>
          <Weekday>금</Weekday>
          <Weekday>토</Weekday>
        </WeekdayRow>
        <tbody>
          {weeks.map((week, i) => (
            <tr key={i}>
              {week.map((date, j) => (
                <Td
                  lastRow={i === weeks.length - 1}
                  key={j}
                  isCurMonth={date.isCurMonth}
                >
                  {date.day}
                </Td>
              ))}
            </tr>
          ))}
        </tbody>
      </Table>
    </CalendarWrapper>
  );
}
