INSERT INTO patient (name, gender, birth_date, email, blood_group)
VALUES
    ('Aziz Lokhandwala', 'Male', '2000-08-08', 'aziz.lokhandwala@example.com', 'O_POSITIVE'),
    ('Neha Patel', 'Female', '1995-04-19', 'neha.patel@example.com', 'A_POSITIVE'),
    ('Harsh Tiwari', 'Male', '2005-01-10', 'harsh.tiwari@example.com', 'A_POSITIVE'),
    ('Karan Sharma', 'Male', '1990-05-23', 'karan.sharma@example.com', 'AB_POSITIVE'),
    ('Yashika Soni', 'Female', '1998-08-05', 'yash.soni@example.com', 'O_POSITIVE');


INSERT INTO doctor (name, specialization, email)
VALUES
    ('Dr. Rakesh Mehta', 'Cardiology', 'rakesh.mehta@example.com'),
    ('Dr. Sneha Kapoor', 'Dermatology', 'sneha.kapoor@example.com'),
    ('Dr. Arjun Nair', 'Orthopedics', 'arjun.nair@example.com');


INSERT INTO appointment (appointment_time, reason, doctor_id, patient_id)
VALUES
  ('2025-07-01 10:30:00', 'General Checkup', 1, 2),
  ('2025-07-02 11:00:00', 'Skin Rash', 2, 2),
  ('2025-07-03 09:45:00', 'Knee Pain', 3, 3),
  ('2025-07-04 14:00:00', 'Follow-up Visit', 1, 1),
  ('2025-07-05 16:15:00', 'Consultation', 1, 4),
  ('2025-07-06 08:30:00', 'Allergy Treatment', 2, 5);