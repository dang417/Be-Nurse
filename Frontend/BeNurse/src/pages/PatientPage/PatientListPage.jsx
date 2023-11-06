import React, { useState, useEffect } from "react";
import { NavLink } from "react-router-dom";

import Input from "@components/atoms/Input/Input";
import PatientItem from "@components/templates/Patient/PatientItem";
import PatientFilterSelect from "@components/templates/Patient/PatientFilterSelect";

import { Common } from "@utils/global.styles.jsx";

import { usePatientStore } from "@store/store";
import { customAxios } from "../../libs/axios";

export default function PatientListPage() {
  const [patients, setPatients] = useState([]);
  const { setSelectedPatient } = usePatientStore();

  useEffect(() => {
    customAxios
      .get("emr/patient/all")
      .then((res) => {
        console.log("환자 목록 불러오기", res.data.responseData);
        setPatients(res.data.responseData);
      })
      .catch((error) => {
        console.error("환자 목록 로드 실패:", error);
      });
    setSelectedPatient({});
  }, []);

  return (
    <div
      style={{
        position: "relative",
        width: "386px",
        marginTop: "84px",
      }}
    >
      <div
        style={{
          background: `${Common.color.purple00}`,
          position: "absolute",
          top: "14px",
          display: "flex",
          flexDirection: "column",
          gap: "12px",
          boxShadow: `0px 0px 10px 10px ${Common.color.purple00} `,
          zIndex: "1",
        }}
      >
        <Input
          width={"356px"}
          variant={"search"}
          placeholder={"환자 이름으로 검색"}
        />
        <PatientFilterSelect />
      </div>
      <div
        style={{
          display: "flex",
          flexDirection: "column",
          alignItems: "flex-start",
          paddingBottom: "50px",
          marginTop: "110px",
          paddingTop: "20px",
          height: "591px",
          overflow: "scroll",
          gap: "15px",
          boxSizing: "border-box",
        }}
      >
        <div
          style={{
            width: "100%",
            display: "flex",
            flexWrap: "wrap",
            justifyContent: "flex-start",
            alignItems: "flex-start",

            gap: "10px",
          }}
        >
          {patients.map((patientInfo) => (
            <NavLink
              to={patientInfo.patient.id + "/detail"}
              key={patientInfo.patient.id}
              onClick={() =>
                setSelectedPatient({
                  ...patientInfo.patient,
                  cc: patientInfo.cc[0] ? patientInfo.cc[0].content : " ",
                })
              }
            >
              <PatientItem
                type="patient"
                patientInfo={{
                  ...patientInfo.patient,
                  cc: patientInfo.cc[0] ? patientInfo.cc[0].content : " ",
                }}
              />
            </NavLink>
          ))}
        </div>
      </div>
    </div>
  );
}
