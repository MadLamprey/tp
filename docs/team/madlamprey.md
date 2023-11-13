---
layout: default.md
title: "Misra Aditya's Project Portfolio Page"
---

# Project: JABPRO
## Overview
JABPro aims to solve the problem of HR managers having to sort through tons of job applications.
It makes their life easier by allowing them to easily fetch important info about job applicants such as their contact details, application status etc. It serves as a one-stop addressbook for job applications.

We aimed for JABPro to be a holistic applications management companion. This meant that its value must not be limited to the storage or filtering of applications - tasks that could be performed by a spreadsheet as well. I felt that there were many other struggles that hiring managers faced, a key one among them being - scheduling interviews. This enabled me to work on the `Events` feature.

Additionally, for the application to be more centred around the needs of our target audience, we needed to modify `AddressBook` in a way that it was contextualised into the workflow of our target audience. Beyond simple addition, deletion and update of candidates [persons in AddressBook], for it to be a suitable tool for hiring managers, we tried to analyse what a hiring manager needs to thoroughly vet a candidate. Then came up the need to associate Linkedin or Github profiles with candidates' information. Since the use of APIs was not permitted, I chose to implement the redirect instead.

## Summary of Contributions
### Code Contribution

* [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=madlamprey&breakdown=true) showcases all code written by me for the JABPro codebase
* [PRs authored by me](https://github.com/AY2324S1-CS2103T-W09-4/tp/pulls?q=is%3Apr+author%3AMadLamprey)

### Enhancements Implemented

1. ***LinkedIn and Github***
* Implemented the ability to add LinkedIn and Github usernames to details of candidates
* Implemented the redirection to the respective accounts on the default browser using the Java awt library
* Amended Person to include attributes of LinkedIn and Github

2. ***Events***
* Introduced Events to JABPro, which allows for addition of events relating to candidates
* Updated the UI to include an Events window to display the events, in order of their occurrence
* Implemented functionality to allow events to be stored as JSON files in an `eventBook` as well
* Updated implementations of existing commands - `delete` and `edit`, to allow for cascading deletion of events, and `clear`.

### Contribution to the UG
* Updated the User Guide for
  * Github/LinkedIn Feature:
    * Adding LinkedIn/Github username
    * Opening LinkedIn/Github profile
  * Events Feature:
    * Adding Events
    * Viewing Events
* Added a UI breakdown to help explain what each part of the interface does/showcases

### Contribution to DG
* Contributed to DG for "must-have" and "good-to-have" user-stories
* Contributed to DG for use cases related to linkedin/github feature or Events feature
* Contributed to DG for architecture diagram for UI, Storage and Model
* Contributed to DG for implementation details of LinkedIn/Github feature and Events feature
* Contributed to DG for test cases for `addL`, `addG`, `linkedin`, `github` and `event` commands, for Instructions on Manual Testing
* Contributed to DG for Redirection Implementation details as part of `Appendix - Effort`
* Contributed to DG for Activity and Sequence diagrams of `addL`, `addG` and `event`.

### Contribution to team-based tasks
* Ensured testing as a whole meets coverage requirements

### Review/mentoring contributions
* Reviewed few PR contributions of team members

### Community
* Identified 10 bugs in fellow team's JAR file [Bug Reports](https://github.com/MadLamprey/ped/issues)
* Posted on forum [Issues](https://github.com/nus-cs2103-AY2324S1/forum/issues?q=is%3Aissue+author%3AMadLamprey+)
